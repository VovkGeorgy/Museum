INSERT INTO tour (theme, type_of_exhibits, duration, cost, image_url, guide_id) VALUES
  ('Italian Renaissance', 'paintings', '1', 300.30,
   'https://www.louvre.fr/sites/default/files/imagecache/278x370/medias/medias_images/images/louvre-saint-jean-baptiste_3.jpg?1321828338',
   1),
  ('The Art of Eating', 'paintings', '2', 200.50,
   'https://www.louvre.fr/sites/default/files/imagecache/278x370/image_60854.jpg?1308122198', 2),
  ('On Horseback through the Louvre', 'different', '1', 250.75,
   'https://www.louvre.fr/sites/default/files/imagecache/278x370/image_60111.jpeg?1308120727', 3),
  ('The Da Vinci Code', 'paintings', '3', 400.75,
   'https://www.louvre.fr/sites/default/files/imagecache/278x370/image_64583.jpg?1308124152', 4),
  ('Osiris, An Ancient Egyptian God', 'antiquities', '1', 150.75,
   'https://www.louvre.fr/sites/default/files/imagecache/278x370/image_1308_0.jpg?1309860410', 5);

INSERT INTO guide (username, password, fio, age, experience, languages) VALUES
  ('guide', '11111', 'testGuide', 99, 1, 'Labudabudab'),
  ('guide1', '11111', 'Guide 1 1', 21, 2, 'EN,FR'),
  ('guide2', '11111', 'Guide 2 2', 22, 3, 'EN,FR,PL'),
  ('guide3', '11111', 'Guide 3 3', 23, 4, 'EN'),
  ('guide4', '11111', 'Guide 4 4', 23, 4, 'RU,CHN'),
  ('guide5', '11111', 'Guide 5 5', 23, 4, 'EN,RU,CHN');


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
   'https://www.louvre.fr/sites/default/files/imagecache/278x370/medias/medias_images/images/louvre-dejeuner-chasse.jpg?1519274597'),
  ('The Breakfast', '1800', 'canvas', '2r32i3f',
   'This is an everyday scene in the life of a rich bourgeois family in the 18th century. In France at this time, Le Déjeuner (the French title of this painting) was eaten in the morning, followed by the dîner at lunchtime and the souper in the evening. The use of chocolate and coffee, which came from the colonies, became increasingly widespread in the wealthy society of the time.',
   'https://www.louvre.fr/sites/default/files/imagecache/278x370/medias/medias_images/images/louvre-dejeuner.jpg?1519273984'),
  ('Leaf of a diptych', '1800', 'steel', '2r13h83',
   'This proud horse, pawing the air with his front hooves, looks as if he wants to break free from the ivory plaque holding him back. See how similar the horse and his rider look. Both are turning towards us, looking in the same direction, and their eyes are the same.',
   'https://www.louvre.fr/sites/default/files/imagecache/278x370/medias/medias_images/images/louvre-feuillet-diptyque-cinq-parties.jpg?1519272468'),
  ('The Epsom Derby, 1821', '1834', 'canvas', 'g932hew',
   'How many horses can you count? The only real difference between them is the color – chestnut, brown, gray, or bay. They are all galloping. Their legs are fully outstretched and they almost seem to be floating along above the ground. ',
   'https://www.louvre.fr/sites/default/files/imagecache/278x370/medias/medias_images/images/louvre-course-chevaux-dit-traditionnellementle.jpg?1321828467'),
  ('Officer of the Chasseurs commanding a charge', '1834', 'canvas', 'fj302g', 'Charge! The rider turns to look back and urge his men into battle. See how the horse, the most important figure in the painting, occupies a bold diagonal line across the picture space. Its precarious, rearing pose and, specially, its terrified expression convey the fear of war, depicted in the distance.',
   'https://www.louvre.fr/sites/default/files/imagecache/278x370/medias/medias_images/images/louvre-officier-chasseurs-cheval-garde.jpg?1519268670'),
  ('Horse bit', '1877', 'bronze', '1rkg39f', 'Can you see how this bronze bit fitted onto the horse’s head? The rider slipped the crossbar into the horse’s mouth and adjusted the plain inner surface of the side plaques against its cheeks. Can you see the rings where the reins went through?',
   'https://www.louvre.fr/sites/default/files/imagecache/278x370/medias/medias_images/images/louvre-mors-barre-transversale-rigide.jpg?1321828466');


INSERT INTO visitor (username, password, fio, age, email) VALUES
  ('visitor', '11111', 'testVisitor', '99', 'test@test.test'),
  ('Visitor1', '12345', 'Namiek F.A.', '22', 'namiek@mail.com'),
  ('Visitor2', '54321', 'Migewf K.R.', '42', 'migewf@mail.com'),
  ('Visitor3', '12r2121e', 'Kirmis L.D.', '53', 'kirmis53@mail.com'),
  ('Visitor4', 'g22r44e11', 'Jingle R.T.', '13', 'jingle@mail.com');

INSERT INTO tour_exhibit (tour_id, exhibit_id) VALUES
  ('1', '1'),
  ('1', '2'),
  ('1', '3'),
  ('1', '4'),
  ('2', '4'),
  ('2', '5'),
  ('2', '6'),
  ('2', '7'),
  ('3', '8'),
  ('3', '9'),
  ('3', '10'),
  ('3', '11');

INSERT INTO tour_visitor (tour_id, visitor_id) VALUES
  ('1', '1'),
  ('1', '2'),
  ('1', '3');

INSERT INTO users (username, password) VALUES
  ('admin', '11111'),
  ('guide', '11111'),
  ('visitor', '11111'),
  ('Visitor1', '12345'),
  ('Visitor2', '54321'),
  ('Visitor3', '12r2121e'),
  ('Visitor4', 'g22r44e11'),
  ('guide1', '11111'),
  ('guide2', '11111'),
  ('guide3', '11111'),
  ('guide4', '11111'),
  ('guide5', '11111');

INSERT INTO roles (name) VALUES
  ('VISITOR'),
  ('GUIDE'),
  ('ADMIN');

INSERT INTO users_roles (user_id, role_id) VALUES
  ('1', '1'),
  ('1', '2'),
  ('1', '3'),
  ('2', '1'),
  ('2', '2'),
  ('3', '1');









