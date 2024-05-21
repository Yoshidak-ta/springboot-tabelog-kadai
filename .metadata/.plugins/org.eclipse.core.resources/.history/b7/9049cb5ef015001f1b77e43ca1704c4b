--categoriesテーブル
INSERT IGNORE INTO categories(id, category_name)VALUE(1, 'ひつまぶし');
INSERT IGNORE INTO categories(id, category_name)VALUE(2, '高浜とりめし');
INSERT IGNORE INTO categories(id, category_name)VALUE(3, '名古屋コーチン親子丼');
INSERT IGNORE INTO categories(id, category_name)VALUE(4, '清須からあげまぶし');
INSERT IGNORE INTO categories(id, category_name)VALUE(5, 'みそかつ');
INSERT IGNORE INTO categories(id, category_name)VALUE(6, '名古屋カレーうどん');
INSERT IGNORE INTO categories(id, category_name)VALUE(7, 'きしめん');
INSERT IGNORE INTO categories(id, category_name)VALUE(8, 'あんかけスパゲティ');
INSERT IGNORE INTO categories(id, category_name)VALUE(9, '犬山ドッグ');
INSERT IGNORE INTO categories(id, category_name)VALUE(10, '天むす');
INSERT IGNORE INTO categories(id, category_name)VALUE(11, '小倉トースト');

--rolesテーブル
INSERT IGNORE INTO roles(id, role_name_1, role_name_2)VALUE(1, 'ROLE_GENERAL', '無料会員');
INSERT IGNORE INTO roles(id, role_name_1, role_name_2)VALUE(2, 'ROLE_PRIME', '有料会員');
INSERT IGNORE INTO roles(id, role_name_1, role_name_2)VALUE(3, 'ROLE_ADMIN_STORE', '店舗管理者');
INSERT IGNORE INTO roles(id, role_name_1, role_name_2)VALUE(4, 'ROLE_ADMIN_APPS', 'アプリ管理者');

--usersテーブル
INSERT IGNORE INTO users(id, user_name, furigana, phone_number, email, password, user_role_id, enabled)VALUE(1, '吉本　太郎', 'ヨシモト　タロウ', '090-5325-5143', 'taro.yoshimoto@nagoya.meshi.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, true);
INSERT IGNORE INTO users(id, user_name, furigana, phone_number, email, password, user_role_id, enabled)VALUE(2, '宮本　二郎', 'ミヤモト　ジロウ', '090-6932-1244', 'jiro.miyamoto@nagoya.meshi.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, true);
INSERT IGNORE INTO users(id, user_name, furigana, phone_number, email, password, user_role_id, enabled)VALUE(3, '智頭　好', 'チズ　ヨシミ', '090-1535-6433', 'yoshimi.chizu@nagoya.meshi.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 3, true);
INSERT IGNORE INTO users(id, user_name, furigana, phone_number, email, password, user_role_id, enabled)VALUE(4, '平野　愛美', 'ヒラノ　アイミ', '090-2234-2576', 'aimi.hirano@nagoya.meshi.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 4, true);
INSERT IGNORE INTO users(id, user_name, furigana, phone_number, email, password, user_role_id, enabled)VALUE(5, '姫野　百合', 'ヒメノ　ユリ', '090-4431-4238', 'yuri.himeno@nagoya.meshi.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 2, true);
INSERT IGNORE INTO users(id, user_name, furigana, phone_number, email, password, user_role_id, enabled)VALUE(6, '堂本　圭太', 'ドウモト　ケイタ', '090-6533-1359', 'keita.domoto@nagoya.meshi.com', 'password', 1, true);
INSERT IGNORE INTO users(id, user_name, furigana, phone_number, email, password, user_role_id, enabled)VALUE(7, '佐藤　華', 'サトウ　ハナ', '090-1121-8765', 'hana.sato@nagoya.meshi.com', 'password', 3, true);
INSERT IGNORE INTO users(id, user_name, furigana, phone_number, email, password, user_role_id, enabled)VALUE(8, '新町　遼太郎', 'シンマチ　リョウタロウ', '090-4444-9876', 'ryotaro.shinmachi@nagoya.meshi.com', 'password', 2, true);
INSERT IGNORE INTO users(id, user_name, furigana, phone_number, email, password, user_role_id, enabled)VALUE(9, '塩田　明子', 'シオダ　アキコ', '080-3324-5314', 'akiko.shioda@nagoya.meshi.com', 'password', 2, true);
INSERT IGNORE INTO users(id, user_name, furigana, phone_number, email, password, user_role_id, enabled)VALUE(10, '菅田　葵', 'スダ　アオイ', '080-5454-5439', 'aoi.suda@nagoya.meshi.com', 'password', 3, true);
INSERT IGNORE INTO users(id, user_name, furigana, phone_number, email, password, user_role_id, enabled)VALUE(11, '羽田　古都音', 'ハネダ　コトネ', '080-5334-6413', 'kotone.haneda@nagoya.meshi.com', 'password', 3, true);

--storesテーブル
INSERT IGNORE INTO stores(id, category_id, store_name, furigana, alphabet, photo_name, description, opening_hour, closing_hour, closed_day, minimum_budget, maximum_budget, address, phone_number, seats)VALUE(1, 1, '炭焼　うな富士　本店', 'スミヤキウナフジホンテン', 'Sumiyaki Unafuji Honten', 'store01.jpg', '並んでも食べたい！絶品さくふわ食感のうなぎ', '11:00', '20:30', '不定休', 1000, 3000, '愛知県名古屋市昭和区X-XX-XX', '052-881-0067', 50);
INSERT IGNORE INTO stores(id, category_id, store_name, furigana, alphabet, photo_name, description, opening_hour, closing_hour, closed_day, minimum_budget, maximum_budget, address, phone_number, seats)VALUE(2, 2, 'なっかち庵', 'ナカッチアン', 'Nakacchian', 'store02.jpg', 'ファミリーにもおすすめ！長年修行したご主人が営む絶品めん処', '11:00', '20:00', '月・火曜日', 3000, 6000, '愛知県高浜市碧海区X-XX-XX', '052-1321-4324', 20);
INSERT IGNORE INTO stores(id, category_id, store_name, furigana, alphabet, photo_name, description, opening_hour, closing_hour, closed_day, minimum_budget, maximum_budget, address, phone_number, seats)VALUE(3, 3, 'とり五鐵', 'トリゴテツ', 'Torigotetsu', 'store03.jpg', '親子丼発祥店の店主が手がける職人技の名古屋コーチン親子丼が味わえる', '11:00', '22:00', '不定休', 2000, 10000, '愛知県名古屋市中村区X-XX-XX', '052-6543-7346', 25);
INSERT IGNORE INTO stores(id, category_id, store_name, furigana, alphabet, photo_name, description, opening_hour, closing_hour, closed_day, minimum_budget, maximum_budget, address, phone_number, seats)VALUE(4, 4, 'プチレストラン　ベル', 'プチレストランベル', 'Puchiresutoran Beru', 'store04.jpg', '清須で愛される憩いの場！他店にはない店主の創作料理が魅力', '7:00', '18:00', '不定休', 1000, 5000, '愛知県清須市西区X-XX-XX', '052-444-5555', 50);
INSERT IGNORE INTO stores(id, category_id, store_name, furigana, alphabet, photo_name, description, opening_hour, closing_hour, closed_day, minimum_budget, maximum_budget, address, phone_number, seats)VALUE(5, 5, 'さくとん', 'サクトン', 'Sakuton', 'store05.jpg', '地元で長年愛される揚げたてサクサクのみそかつが自慢のさくとん', '11:00', '22:00', '日曜日', 1000, 5000, '愛知県名古屋市昭和区X-XX-XX', '052-6423-3214', 10);
INSERT IGNORE INTO stores(id, category_id, store_name, furigana, alphabet, photo_name, description, opening_hour, closing_hour, closed_day, minimum_budget, maximum_budget, address, phone_number, seats)VALUE(6, 6, '本店 鯱乃家', 'ホンテンシャチノヤ', 'Honten Syachinoya', 'store06.jpg', 'これぞ元祖の味！カレーがよく絡むちゅるちゅるの極太麺', '11:00', '21:00', '木曜日', 1000, 2000, '愛知県名古屋市北区X-XX-XX', '052-652-6246', 30);
INSERT IGNORE INTO stores(id, category_id, store_name, furigana, alphabet, photo_name, description, opening_hour, closing_hour, closed_day, minimum_budget, maximum_budget, address, phone_number, seats)VALUE(7, 7, '星が丘製麺所', 'ホシガオカセイメンジョ', 'Hoshigaoka Seimenjo', 'store07.jpg', 'デートにもおすすめ！解放感と木のぬくもりを感じられる店内も魅力', '11:00', '20:00', '不定休', 600, 1500, '愛知県名古屋市千種区X-XX-XX', '052-431-5431', 50);
INSERT IGNORE INTO stores(id, category_id, store_name, furigana, alphabet, photo_name, description, opening_hour, closing_hour, closed_day, minimum_budget, maximum_budget, address, phone_number, seats)VALUE(8, 8, 'スパゲティ・ハウス　ヨコイ　住吉本店', 'スパゲティハウスヨコイスミヨシホンテン', 'Supagethi House yokoi Sumiyoshihonten', 'store08.jpg', 'あんかけスパゲティの元祖！発祥の店でいただける本物の味', '11:00', '21:00', '月曜日', 1000, 7000, '愛知県名古屋市中区X-XX', '052-432-4128', 20);
INSERT IGNORE INTO stores(id, category_id, store_name, furigana, alphabet, photo_name, description, opening_hour, closing_hour, closed_day, minimum_budget, maximum_budget, address, phone_number, seats)VALUE(9, 9, '結屋', 'ユイヤ', 'Yuiya', 'store09.jpg', '食欲そそる！溢れんばかりに盛られた「炭火焼きもち豚」が絶品', '10:00', '17:00', '不定休', 500, 1000, '愛知県犬山市北区X-XX', '052-431-4219', 5);
INSERT IGNORE INTO stores(id, category_id, store_name, furigana, alphabet, photo_name, description, opening_hour, closing_hour, closed_day, minimum_budget, maximum_budget, address, phone_number, seats)VALUE(10, 10, '天むす　千寿　大須本店', 'テンムス　センジュ　オオスホンテン', 'Tenmusu Senju Osuhonten', 'store10.jpg', '三重発祥の天むすを名古屋で広めた老舗の名店', '08:00', '18:00', '火・水曜日', 500, 1500, '愛知県名古屋市中区XXX', '052-431-4130', 10);
INSERT IGNORE INTO stores(id, category_id, store_name, furigana, alphabet, photo_name, description, opening_hour, closing_hour, closed_day, minimum_budget, maximum_budget, address, phone_number, seats)VALUE(11, 11, '喫茶モーニング', 'キッサモーニング', 'Kissamo-ningu', 'store11.jpg', 'ゆったりとした古民家カフェで心も体も満たされるモーニングを', '08:00', '16:00', '火曜日', 500, 1200, '愛知県名古屋市中村区XXX-OO', '052-113-4230', 30);


--reservesテーブル
INSERT IGNORE INTO reserves(id, user_id, store_id, booking_date, booking_time, number_of_people)VALUE(1, 1, 1, '2024-06-01', '17:00', 4);
INSERT IGNORE INTO reserves(id, user_id, store_id, booking_date, booking_time, number_of_people)VALUE(2, 1, 1, '2024-06-02', '17:00', 4);
INSERT IGNORE INTO reserves(id, user_id, store_id, booking_date, booking_time, number_of_people)VALUE(3, 1, 1, '2024-06-03', '17:00', 4);
INSERT IGNORE INTO reserves(id, user_id, store_id, booking_date, booking_time, number_of_people)VALUE(4, 1, 1, '2024-06-04', '17:00', 4);
INSERT IGNORE INTO reserves(id, user_id, store_id, booking_date, booking_time, number_of_people)VALUE(5, 1, 1, '2024-06-05', '17:00', 4);
INSERT IGNORE INTO reserves(id, user_id, store_id, booking_date, booking_time, number_of_people)VALUE(6, 1, 1, '2024-06-06', '17:00', 4);
INSERT IGNORE INTO reserves(id, user_id, store_id, booking_date, booking_time, number_of_people)VALUE(7, 1, 2, '2024-06-01', '17:00', 4);
INSERT IGNORE INTO reserves(id, user_id, store_id, booking_date, booking_time, number_of_people)VALUE(8, 1, 1, '2024-06-01', '18:00', 4);
INSERT IGNORE INTO reserves(id, user_id, store_id, booking_date, booking_time, number_of_people)VALUE(9, 1, 3, '2024-06-01', '17:00', 4);
INSERT IGNORE INTO reserves(id, user_id, store_id, booking_date, booking_time, number_of_people)VALUE(10, 1, 2, '2024-06-02', '17:00', 4);
INSERT IGNORE INTO reserves(id, user_id, store_id, booking_date, booking_time, number_of_people)VALUE(11, 1, 2, '2024-06-03', '17:00', 4);
INSERT IGNORE INTO reserves(id, user_id, store_id, booking_date, booking_time, number_of_people)VALUE(12, 2, 1, '2024-06-01', '17:00', 4);

--reviewsテーブル
INSERT IGNORE INTO reviews(id, user_id, store_id, stars, review_comment)VALUE(1, 1, 1, '★★★★★', '雰囲気のいいカフェでした。');
INSERT IGNORE INTO reviews(id, user_id, store_id, stars, review_comment)VALUE(2, 1, 2, '★★★★★', 'おいしかったです。');
INSERT IGNORE INTO reviews(id, user_id, store_id, stars, review_comment)VALUE(3, 1, 3, '★★★★★', 'よかったです。');
INSERT IGNORE INTO reviews(id, user_id, store_id, stars, review_comment)VALUE(4, 1, 4, '★★★★★', 'よかったです。');
INSERT IGNORE INTO reviews(id, user_id, store_id, stars, review_comment)VALUE(5, 1, 5, '★★★★★', '雰囲気のいいカフェでした。');
INSERT IGNORE INTO reviews(id, user_id, store_id, stars, review_comment)VALUE(6, 1, 6, '★★★★★', '雰囲気のいいカフェでした。');
INSERT IGNORE INTO reviews(id, user_id, store_id, stars, review_comment)VALUE(7, 1, 7, '★★★★★', '雰囲気のいいカフェでした。');
INSERT IGNORE INTO reviews(id, user_id, store_id, stars, review_comment)VALUE(8, 1, 8, '★★★★★', '雰囲気のいいカフェでした。');
INSERT IGNORE INTO reviews(id, user_id, store_id, stars, review_comment)VALUE(9, 1, 9, '★★★★★', '雰囲気のいいカフェでした。');
INSERT IGNORE INTO reviews(id, user_id, store_id, stars, review_comment)VALUE(10, 1, 10, '★★★★★', '雰囲気のいいカフェでした。');
INSERT IGNORE INTO reviews(id, user_id, store_id, stars, review_comment)VALUE(11, 1, 11, '★★★★★', '雰囲気のいいカフェでした。');
INSERT IGNORE INTO reviews(id, user_id, store_id, stars, review_comment)VALUE(12, 2, 1, '★★★☆☆', 'おいしかったです。');

--favoritesテーブル
INSERT IGNORE INTO favorites(id, user_id, store_id)VALUE(1, 1, 1);
INSERT IGNORE INTO favorites(id, user_id, store_id)VALUE(2, 1, 2);
INSERT IGNORE INTO favorites(id, user_id, store_id)VALUE(3, 1, 3);
INSERT IGNORE INTO favorites(id, user_id, store_id)VALUE(4, 1, 4);
INSERT IGNORE INTO favorites(id, user_id, store_id)VALUE(5, 1, 5);
INSERT IGNORE INTO favorites(id, user_id, store_id)VALUE(6, 1, 6);
INSERT IGNORE INTO favorites(id, user_id, store_id)VALUE(7, 1, 7);
INSERT IGNORE INTO favorites(id, user_id, store_id)VALUE(8, 1, 8);
INSERT IGNORE INTO favorites(id, user_id, store_id)VALUE(9, 1, 9);
INSERT IGNORE INTO favorites(id, user_id, store_id)VALUE(10, 1, 10);
INSERT IGNORE INTO favorites(id, user_id, store_id)VALUE(11, 1, 11);
INSERT IGNORE INTO favorites(id, user_id, store_id)VALUE(12, 2, 1);