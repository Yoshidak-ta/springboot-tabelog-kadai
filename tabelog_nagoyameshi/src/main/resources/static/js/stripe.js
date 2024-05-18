const stripe = Stripe('pk_live_51OU9oYG3807R4hwqQxH9An5ZjqKHOCYT9VyOVVDX1YP4pIt8ySNJEHkmXEAAdJD3OZbWRALaJXEyUPF2AfgBg3KH00PDLZPtDW');
const paymentButton = document.querySelector('#paymentButton');
const updateButton = document.querySelector('#updateButton');

if (paymentButton) {
	paymentButton.addEventListener('click', () => {
		stripe.redirectToCheckout({
			sessionId: sessionId
		})
	});
}

if (updateButton) {
	updateButton.addEventListener('click', () =>  {
		if(portalSessionUrl  && portalSessionUrl !== "portalSessionUrl"){
			window.location.href = portalSessionUrl; // カスタマーポータルのURLにリダイレクト
		}else{
			console.log('カスタマーポータルセッションのURLが見つかりません');
		}
	});
}