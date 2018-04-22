INSERT INTO tour (theme, type_of_exhibits, duration, cost, image_url) VALUES
  ('Italian Renaissance', 'paintings', '1', 300.30,
   'https://www.louvre.fr/sites/default/files/imagecache/278x370/medias/medias_images/images/louvre-saint-jean-baptiste_3.jpg?1321828338'),
  ('The Art of Eating', 'paintings', '2', 200.50,
   'https://www.louvre.fr/sites/default/files/imagecache/278x370/image_60854.jpg?1308122198'),
  ('On Horseback through the Louvre', 'paintings', '1', 250.75,
   'https://www.louvre.fr/sites/default/files/imagecache/278x370/image_60111.jpeg?1308120727'),
  ('The Da Vinci Code', 'paintings', '3', 400.75,
   'https://www.louvre.fr/sites/default/files/imagecache/278x370/image_64583.jpg?1308124152'),
  ('Osiris, An Ancient Egyptian God', 'antiquities', '1', 150.75,
   'https://www.louvre.fr/sites/default/files/imagecache/278x370/image_1308_0.jpg?1309860410');

INSERT INTO guide (fio, age, experience, languages, tour_id) VALUES
  ('Guide 1 1', 21, 2, 'EN,FR', 1),
  ('Guide 2 2', 22, 3, 'EN,FR,PL', 2),
  ('Guide 3 3', 23, 4, 'EN', 3),
  ('Guide 4 4', 23, 4, 'RU,CHN', 4),
  ('Guide 5 5', 23, 4, 'EN,RU,CHN', 5);


INSERT INTO exhibit (title, dated, material, archive_num, description, image_url) VALUES
  ('St. Francis of Assisi Receiving the Stigmata', '1300', 'canvas', 'as2d21s',
   'The theme of this altarpiece, painted in 1300 for a church in Pisa, is a well-known episode from the life of St. Francis of Assisi related by his companions: the miracle of the stigmata on Mount La Verna. Giotto''s vision was in harmony with Franciscan spirituality, and having to appeal to the greatest number of believers, he set the main scene in a landscape of trees and rocks.',
   'https://www.louvre.fr/sites/default/files/imagecache/278x370/medias/medias_images/images/louvre-saint-francois-d039assise-recevant.jpg?1519275049'),
  ('The Coronation of the Virgin', '1435', 'canvas', 'as2d2w12f1s',
   'Painted before 1435 for the church of San Domenico in Fiesole, where Fra Angelico had taken his vows, the theme of this altarpiece was not drawn from the canonical Gospels, but from the Apocrypha. The scene occurs in Paradise, and the Virgin is crowned by Christ, surrounded by the heavenly host of angelic musicians, saints and martyrs.',
   'https://www.louvre.fr/sites/default/files/imagecache/278x370/medias/medias_images/images/louvre-couronnement-vierge_0.jpg?1519274996'),
  ('The Battle of San Romano', '1432', 'canvas', 'as12121f1s',
   'Commissioned by the Salimbeni, a Florentine family, Paolo Uccello painted three panels on the theme of the Battle of San Romano, a skirmish between Florentine and Sienese mercenaries that took place in 1432. These three works were later hung in the Palazzo Medici, in Via Larga. Today they are divided between the Uffizi, Florence, the National Gallery, London, and the Louvre.',
   'https://www.louvre.fr/sites/default/files/imagecache/278x370/medias/medias_images/images/louvre-bataille-san-romano-contre.jpg?1519269219'),
  ('The Wedding Feast at Cana', '1562', 'canvas', '2f13g12',
   'In Venice during the 16th century, the banquet was a veritable spectacle. The U-shaped table made it possible for musicians and jesters to perform in the center. Meals were served in six to eight carefully ordered courses, organized by a steward.',
   'https://www.louvre.fr/sites/default/files/imagecache/278x370/medias/medias_images/images/louvre-les-noces-cana.jpg?1519269004'),
  ('The Peasant Meal', '17th', 'canvas', 'f4hjl8',
   'In the 17th century, genre scenes like this Peasant Meal often had a symbolic meaning. Were they accurate portrayals of contemporary peasant life or religious metaphors? The bread and wine, depicted alone on a table, recall the eucharist. The central figure in these works can often be seen as Christ.',
   'https://www.louvre.fr/sites/default/files/imagecache/278x370/medias/medias_images/images/louvre-repas-paysans.jpg?1321828496'),
  ('The Hunt Luncheon', '18th', 'canvas', '14f4ff',
   'In 18th-century France, meals were elaborate affairs, with an ordered sequence of dishes and a particular way of serving them. There were numerous dishes, which were brought to the table in a series of waves, called services. Soup and appetizers were followed by roasts and salads, which were followed by desserts. The meal ended with fruit.',
   'https://www.louvre.fr/sites/default/files/imagecache/278x370/medias/medias_images/images/louvre-dejeuner-chasse.jpg?1519274597');


INSERT INTO visitor (username, password, fio, age, email) VALUES
  ('Visitor1', '12345', 'Namiek F.A.', '22', 'namiek@mail.com'),
  ('Visitor2', '54321', 'Migewf K.R.', '42', 'migewf@mail.com'),
  ('Visitor3', '12r2121e', 'Kirmis L.D.', '53', 'kirmis53@mail.com'),
  ('Visitor4', 'g22r44e11', 'Jingle R.T.', '13', 'jingle@mail.com');








