-- Brand
INSERT INTO brand(name,description,created_at) VALUES ('ADIDAS','ADIDAS SHOES BRAND',CURRENT_TIMESTAMP());
INSERT INTO brand(name,description,created_at) VALUES ('NIKE','NIKE SHOES BRAND',CURRENT_TIMESTAMP());
INSERT INTO brand(name,description,created_at) VALUES ('PAN','PAN SHOES BRAND',CURRENT_TIMESTAMP());
INSERT INTO brand(name,description,created_at) VALUES ('REEBOK','REEBOK SHOES BRAND',CURRENT_TIMESTAMP());

-- Product
INSERT INTO product(name,description,price,discount,full_price,shop,image,created_at,brand_id,warranty,promotion_start,promotion_end) VALUES ('adidas NMD V3 Retrieves The OG NMD Colorway','Curb & Gutter',100,0.2,125,'Enewetak','http://dummyimage.com/186x100.png/5fa2dd/ffffff',CURRENT_TIMESTAMP(),1,'2 Weeks',CURRENT_TIMESTAMP(),DATEADD(month,1,CURRENT_TIMESTAMP));
INSERT INTO product(name,description,price,discount,full_price,shop,image,created_at,brand_id,warranty,promotion_start,promotion_end) VALUES ('adidas NMD_R1 BOBA FETT SPECTOO SHOES','Site Furnishings',200,0.2,250,'Al Balyanā','http://dummyimage.com/245x100.png/dddddd/000000',CURRENT_TIMESTAMP(),1,'2 Weeks',CURRENT_TIMESTAMP(),DATEADD(month,1,CURRENT_TIMESTAMP));
INSERT INTO product(name,description,price,discount,full_price,shop,image,created_at,brand_id,warranty,promotion_start,promotion_end) VALUES ('Coffee adidas NMD Swiss Choc Almond','Structural & Misc Steel Erection',300,0.2,375,'Urachiche','http://dummyimage.com/130x100.png/5fa2dd/ffffff',CURRENT_TIMESTAMP(),1,'2 Weeks',CURRENT_TIMESTAMP(),DATEADD(month,1,CURRENT_TIMESTAMP));
INSERT INTO product(name,description,price,discount,full_price,shop,image,created_at,brand_id,warranty,promotion_start,promotion_end) VALUES ('Marjoram adidas NMD Fresh','HVAC',400,0.2,500,'Nador','http://dummyimage.com/230x100.png/5fa2dd/ffffff',CURRENT_TIMESTAMP(),1,'2 Weeks',CURRENT_TIMESTAMP(),DATEADD(month,1,CURRENT_TIMESTAMP));
INSERT INTO product(name,description,price,discount,full_price,shop,image,created_at,brand_id,warranty,promotion_start,promotion_end) VALUES ('Seaweed adidas NMD Green Sheets','Curb & Gutter',500,0.2,625,'Shuishi','http://dummyimage.com/211x100.png/dddddd/000000',CURRENT_TIMESTAMP(),1,'2 Weeks',CURRENT_TIMESTAMP(),DATEADD(month,1,CURRENT_TIMESTAMP));
INSERT INTO product(name,description,price,discount,full_price,shop,image,created_at,brand_id,warranty,promotion_start,promotion_end) VALUES ('Macaroons NIKE SHOES Two Bite Choc','Masonry',600,0.2,750,'Bogoroditsk','http://dummyimage.com/204x100.png/dddddd/000000',CURRENT_TIMESTAMP(),2,'2 Weeks',CURRENT_TIMESTAMP(),DATEADD(month,1,CURRENT_TIMESTAMP));
INSERT INTO product(name,description,price,discount,full_price,shop,image,created_at,brand_id,warranty,promotion_start,promotion_end) VALUES ('Pastry PAN SHOES Chocolate Marble Tea','Construction Clean and Final Clean',700,0.2,875,'Achiaman','http://dummyimage.com/140x100.png/5fa2dd/ffffff',CURRENT_TIMESTAMP(),3,'2 Weeks',CURRENT_TIMESTAMP(),DATEADD(month,1,CURRENT_TIMESTAMP));
INSERT INTO product(name,description,price,discount,full_price,shop,image,created_at,brand_id,warranty,promotion_start,promotion_end) VALUES ('Nut - PAN Pistachio, Shelled','EIFS',800,0.2,900,'Salerno','http://dummyimage.com/161x100.png/dddddd/000000',CURRENT_TIMESTAMP(),3,'2 Weeks',CURRENT_TIMESTAMP(),DATEADD(month,1,CURRENT_TIMESTAMP));
INSERT INTO product(name,description,price,discount,full_price,shop,image,created_at,brand_id,warranty,promotion_start,promotion_end) VALUES ('Muffin REEBOK - Individual','Retaining Wall and Brick Pavers',900,0.2,1125,'Huangge','http://dummyimage.com/172x100.png/dddddd/000000',CURRENT_TIMESTAMP(),4,'2 Weeks',CURRENT_TIMESTAMP(),DATEADD(month,1,CURRENT_TIMESTAMP));
INSERT INTO product(name,description,price,discount,full_price,shop,image,created_at,brand_id,warranty,promotion_start,promotion_end) VALUES ('Nantucket REEBOK Orange banana','Site Furnishings',1000,0.2,1250,'Lokokrangan','http://dummyimage.com/224x100.png/ff4444/ffffff',CURRENT_TIMESTAMP(),4,'2 Weeks',CURRENT_TIMESTAMP(),DATEADD(month,1,CURRENT_TIMESTAMP));

-- Product Available
INSERT INTO product_available(size,quantity,created_at,product_id) VALUES (35,10,CURRENT_TIMESTAMP(),1);
INSERT INTO product_available(size,quantity,created_at,product_id) VALUES (40,10,CURRENT_TIMESTAMP(),1);
INSERT INTO product_available(size,quantity,created_at,product_id) VALUES (33,11,CURRENT_TIMESTAMP(),2);
INSERT INTO product_available(size,quantity,created_at,product_id) VALUES (38,15,CURRENT_TIMESTAMP(),2);
INSERT INTO product_available(size,quantity,created_at,product_id) VALUES (38,100,CURRENT_TIMESTAMP(),3);
INSERT INTO product_available(size,quantity,created_at,product_id) VALUES (36,12,CURRENT_TIMESTAMP(),3);
INSERT INTO product_available(size,quantity,created_at,product_id) VALUES (35,13,CURRENT_TIMESTAMP(),4);
INSERT INTO product_available(size,quantity,created_at,product_id) VALUES (35,14,CURRENT_TIMESTAMP(),5);
INSERT INTO product_available(size,quantity,created_at,product_id) VALUES (35,15,CURRENT_TIMESTAMP(),6);
INSERT INTO product_available(size,quantity,created_at,product_id) VALUES (35,6,CURRENT_TIMESTAMP(),7);
INSERT INTO product_available(size,quantity,created_at,product_id) VALUES (35,32,CURRENT_TIMESTAMP(),8);
INSERT INTO product_available(size,quantity,created_at,product_id) VALUES (35,1,CURRENT_TIMESTAMP(),9);
INSERT INTO product_available(size,quantity,created_at,product_id) VALUES (35,8,CURRENT_TIMESTAMP(),10);


