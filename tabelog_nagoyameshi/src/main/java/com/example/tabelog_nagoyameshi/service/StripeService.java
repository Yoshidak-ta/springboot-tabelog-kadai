package com.example.tabelog_nagoyameshi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.tabelog_nagoyameshi.entity.Card;
import com.example.tabelog_nagoyameshi.entity.User;
import com.example.tabelog_nagoyameshi.repository.CardRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.Event;
import com.stripe.model.StripeObject;
import com.stripe.model.Subscription;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class StripeService {
	@Value("${stripe.api-key}")
	private String stripeApiKey;
	
	private final CardService cardService;
	private final UserService userService;
	private final CardRepository cardRepository;
	
	public StripeService(CardService cardService, UserService userService, CardRepository cardRepository) {
		this.cardService = cardService;
		this.userService = userService;
		this.cardRepository = cardRepository;
	}
	
	//セッションを作成し、Stripeに必要な情報を返す（アップグレード）
	public String updateStripeSession(HttpServletRequest httpServletRequest) {
		Stripe.apiKey = stripeApiKey;
		String requestUrl = new String(httpServletRequest.getRequestURL());
		SessionCreateParams params = 
				SessionCreateParams.builder()
				
					.setSuccessUrl(requestUrl.replaceAll("/login/mypage", "") + "/login/mypage?upgrade")
					.setCancelUrl(requestUrl.replace("login/mypage", ""))
					.setMode(SessionCreateParams.Mode.SUBSCRIPTION)
					.addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
					.addLineItem(
							SessionCreateParams.LineItem.builder()
								.setQuantity(1L)
								.setPrice("price_1PHlpEG3807R4hwqIh3PGdoj")
								.build()
								)
					.build();
		
		try {
			Session session = Session.create(params);
			return session.getId();
		}catch (StripeException e){
			e.printStackTrace();
			return "";
		}
	}
	
	// セッションからメールアドレスと顧客IDを取得し、CardServiceクラスを介してデータベースに登録する
	public void processSessionCompleted(Event event) {
		Optional<StripeObject> optionalStripeObject = event.getDataObjectDeserializer().getObject();
		optionalStripeObject.ifPresentOrElse(stripeObject -> {
			System.out.println("成功");
			System.out.println("Stripe API Version: " + event.getApiVersion());
			System.out.println("stripe-java Version: " + Stripe.VERSION);
			Session session = (Session)stripeObject;
			String customerId = session.getCustomer();
			String subscriptionId = session.getSubscription();
			
			try {
				Customer customer = Customer.retrieve(customerId);
				String email = customer.getEmail();
				
				cardService.create(email, customerId, subscriptionId);
				userService.roleUpdate(email);
			}catch (StripeException e){
				e.printStackTrace();
			}
		},
		() -> {
			System.out.println("失敗");
			System.out.println("Stripe API Version: " + event.getApiVersion());
			System.out.println("stripe-java Version: " + Stripe.VERSION);
		});
	}

	
	//カスタマーポータルセッションの作成
	public String portalStripeSession(User user, HttpServletRequest httpServletRequest) {
		Stripe.apiKey = stripeApiKey;
		String requestUrl = new String(httpServletRequest.getRequestURL());
		Card card = cardRepository.findByUser(user);
		String customer = card.getCustomerId();
		
		com.stripe.param.billingportal.SessionCreateParams params =
				com.stripe.param.billingportal.SessionCreateParams.builder()
					.setReturnUrl(requestUrl.replaceAll("/login/mypage/subscription", "") + "/login/mypage?update")
					.setCustomer(customer)
					.build();
		
		try {
			com.stripe.model.billingportal.Session portalSession = com.stripe.model.billingportal.Session.create(params);
			return portalSession.getUrl();
		}catch (StripeException e){
			e.printStackTrace();
			return "";
		}
		
	}
	
	//サブスクキャンセル時のデータ削除
	public void delete(Event event) {
		Optional<StripeObject> optionalStripeObject = event.getDataObjectDeserializer().getObject();
		optionalStripeObject.ifPresent(stripeObject -> {
			Subscription subscription = (Subscription)stripeObject;
			String subscriptionId = subscription.getId();
			
			cardRepository.deleteBySubscriptionId(subscriptionId);
		});
	}

}