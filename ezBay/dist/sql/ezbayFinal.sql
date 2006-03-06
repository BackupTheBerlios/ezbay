DELETE FROM actiontransaction;
DELETE FROM actionenchere;
DELETE FROM article;
DELETE FROM membre;
DELETE FROM vendeur;
DELETE FROM client;
DELETE FROM categorie;

# Catégories
INSERT INTO categorie (id, libelle) VALUES('art', 'Art et Antiquités');
INSERT INTO categorie (id, libelle) VALUES('veh', 'Automobile, Moto et Pièces');
INSERT INTO categorie (id, libelle) VALUES('bat', 'Bateaux, Voile, Nautisme');
INSERT INTO categorie (id, libelle) VALUES('beau', 'Beauté, Bien-être, Parfums');
INSERT INTO categorie (id, libelle) VALUES('beb', 'Bébé, Puériculture');
INSERT INTO categorie (id, libelle) VALUES('bij', 'Bijoux Montres');
INSERT INTO categorie (id, libelle) VALUES('music', 'CD, Vinyles et Musique');
INSERT INTO categorie (id, libelle) VALUES('verres', 'Céramiques et Verres');
INSERT INTO categorie (id, libelle) VALUES('collec', 'Collections');
INSERT INTO categorie (id, libelle) VALUES('cine', 'DVD et Cinéma');
INSERT INTO categorie (id, libelle) VALUES('info', 'Informatique et PDA');
INSERT INTO categorie (id, libelle) VALUES('instrum', 'Instruments de Musique');
INSERT INTO categorie (id, libelle) VALUES('jouets', 'Jeux, Jouets et Figurines');
INSERT INTO categorie (id, libelle) VALUES('jeuxvid', 'Jeux Vidéo Consoles');
INSERT INTO categorie (id, libelle) VALUES('livres', 'Livres, BD et Revues');
INSERT INTO categorie (id, libelle) VALUES('loisirs', 'Loisirs Créatifs');
INSERT INTO categorie (id, libelle) VALUES('maisons', 'Maison, Jardin, Bricolage');
INSERT INTO categorie (id, libelle) VALUES('monnaie', 'Monnaies');
INSERT INTO categorie (id, libelle) VALUES('photo', 'Photo et Vidéo');
INSERT INTO categorie (id, libelle) VALUES('pme', 'PME Artisans Agriculteurs');
INSERT INTO categorie (id, libelle) VALUES('sport', 'Sports et Vacances');
INSERT INTO categorie (id, libelle) VALUES('tel', 'Téléphonie');
INSERT INTO categorie (id, libelle) VALUES('timbres', 'Timbres');
INSERT INTO categorie (id, libelle) VALUES('tv', 'TV Son Home-Cinema');
INSERT INTO categorie (id, libelle) VALUES('vetement', 'Vêtements et Accessoires');
INSERT INTO categorie (id, libelle) VALUES('vins', 'Vins et Gastronomie');

#clients
INSERT INTO client VALUES('axel_client');
INSERT INTO client VALUES('lotfi_client);
INSERT INTO client VALUES('sofi_client');
INSERT INTO client VALUES('invite_client');

#vendeurs
INSERT INTO vendeur (id, nomProprioCB, numCB, dateExpirCB, codeSecuCB) VALUES('axel_vendeur', 'ammüller', '4978170049781700', '2006-12-24', '111');
INSERT INTO vendeur (id, nomProprioCB, numCB, dateExpirCB, codeSecuCB) VALUES('lotfi_vendeur', 'elgherbi', '4978170012345678', '2007-06-01', '222');
INSERT INTO vendeur (id, nomProprioCB, numCB, dateExpirCB, codeSecuCB) VALUES('sofi_vendeur', 'lion', '4978170078901234', '2006-10-24', '333');

#membres
INSERT INTO membre (id, nom, prenom, pseudo, password, email, adresse, codePostal, ville, pays, telePhoneFixe, telePhonePortable, dateNaissance, vendeur_id, client_id) VALUES('axel_membre', 'ammüller', 'axel', 'axel', 'axel', 'axel@dauphine.fr', 'Levallois', '92000', 'Paris', 'France', '0690900990', '0129384738', '1980-08-26', 'axel_vendeur', 'axel_client'); 
INSERT INTO membre (id, nom, prenom, pseudo, password, email, adresse, codePostal, ville, pays, telePhoneFixe, dateNaissance, vendeur_id, client_id) VALUES('lotfi_membre', 'elgherbi', 'lotfi', 'lotfi', 'lotfi', 'lotfi@dauphine.fr', 'Anvers', '75018', 'Paris', 'France', '0149876556', '1982-12-05', 'lotfi_vendeur', 'lotfi_client'); 
INSERT INTO membre (id, nom, prenom, pseudo, password, email, adresse, codePostal, ville, pays, telePhoneFixe, dateNaissance, vendeur_id, client_id) VALUES('sofi_membre', 'lion', 'sofi', 'sofi', 'sofi', 'sofi@dauphine.fr', 'Jasmin', '75016', 'Paris', 'France', '0142948574', '1983-02-26', 'sofi_vendeur', 'sofi_client'); 
INSERT INTO membre (id, nom, prenom, pseudo, password, email, adresse, codePostal, ville, pays, telePhonePortable, dateNaissance, client_id) VALUES('invite_membre', 'invite', 'invite', 'invite', 'invite', 'invite@dauphine.fr', 'Dauphine', '75000', 'Paris', 'France', '0687878787', '1979-11-01', 'invite_client'); 


#articles
INSERT INTO article (id, envente, libelle, marque, modele, prixvente, anneefabrication, description, datelimite, categorie_id, vendeur_id, nbEncheres, vendeurPseudo, vendeurId, vendeurMembreId) VALUES('axel_article1', '0' , 'DVD', 'Pathé', 'DVD', '2.00', '2004', 'Les Simpsons en folie', '2006-03-16', 'cine', 'axel_vendeur', 0, 'axel', 'axel_vendeur', 'axel_membre');
INSERT INTO article (id, envente, libelle, marque, modele, prixvente, anneefabrication, description, datelimite, categorie_id, vendeur_id, nbEncheres, vendeurPseudo, vendeurId, vendeurMembreId) VALUES('axel_article2', '1' , 'Appareil photos', 'Sony', 'S350-X', '250.00', '2005', 'Appareil photo numérique haut de gamme', '2006-03-18', 'photo', 'axel_vendeur', 1, 'axel', 'axel_vendeur', 'axel_membre');
INSERT INTO article (id, envente, libelle, marque, modele, prixvente, anneefabrication, description, datelimite, categorie_id, vendeur_id, nbEncheres, vendeurPseudo, vendeurId, vendeurMembreId) VALUES('axel_article3', '1' , 'Lecteur MP3', 'Apple', 'Ipod', '300.00', '2006', 'Ipod dernière génération - neuf', '2006-03-08', 'tv', 'axel_vendeur', 1, 'axel', 'axel_vendeur', 'axel_membre');
INSERT INTO article (id, envente, libelle, marque, modele, prixvente, anneefabrication, description, datelimite, categorie_id, vendeur_id, nbEncheres, vendeurPseudo, vendeurId, vendeurMembreId) VALUES('axel_article4', '0' , 'DVD', 'Pathé', 'DVD', '4.00', '2003', 'Les Simpsons en folie 2', '2006-03-09', 'cine', 'axel_vendeur', 0, 'axel', 'axel_vendeur', 'axel_membre');

INSERT INTO article (id, envente, libelle, marque, modele, prixvente, anneefabrication, description, datelimite, categorie_id, vendeur_id, nbEncheres, vendeurPseudo, vendeurId, vendeurMembreId) VALUES('lotfi_article1', '1' , 'AUDI TT', 'Audi', 'TT', '15000.00', '2004', 'Superbe Audi TT noire métallisée - 13000km', '2006-03-10', 'veh', 'lotfi_vendeur', 0, 'lotfi', 'lotfi_vendeur', 'lotfi_membre');
INSERT INTO article (id, envente, libelle, marque, modele, prixvente, anneefabrication, description, datelimite, categorie_id, vendeur_id, nbEncheres, vendeurPseudo, vendeurId, vendeurMembreId) VALUES('lotfi_article2', '1' , 'Mercedes M6', 'Mercedes', 'M6', '23000.00', '2005', 'Splendide Mercedes - occasion à saisir', '2006-03-11', 'veh', 'lotfi_vendeur', 2, 'lotfi', 'lotfi_vendeur', 'lotfi_membre');
INSERT INTO article (id, envente, libelle, marque, modele, prixvente, anneefabrication, description, datelimite, categorie_id, vendeur_id, nbEncheres, vendeurPseudo, vendeurId, vendeurMembreId) VALUES('lotfi_article3', '1' , 'DVD', 'Gaumont', 'DVD', '3.00', '2006', 'Les Simpsons : back to the future', '2006-03-14', 'cine', 'lotfi_vendeur', 1, 'lotfi', 'lotfi_vendeur', 'lotfi_membre');
INSERT INTO article (id, envente, libelle, marque, modele, prixvente, anneefabrication, description, datelimite, categorie_id, vendeur_id, nbEncheres, vendeurPseudo, vendeurId, vendeurMembreId) VALUES('lotfi_article4', '0' , 'DVD', 'Gaumont', 'DVD', '5.00', '2005', 'Les SImpsons : the best', '2006-03-12', 'cine', 'lotfi_vendeur', 0, 'lotfi', 'lotfi_vendeur', 'lotfi_membre');

INSERT INTO article (id, envente, libelle, marque, modele, prixvente, anneefabrication, description, datelimite, categorie_id, vendeur_id, nbEncheres, vendeurPseudo, vendeurId, vendeurMembreId) VALUES('sofi_article1', '0' ,'DVD', 'Pathé', 'DVD', '2.00', '2002', 'La famille Simpson T1', '2006-03-09', 'cine', 'sofi_vendeur', 0, 'sofi', 'sofi_vendeur', 'sofi_membre');
INSERT INTO article (id, envente, libelle, marque, modele, prixvente, anneefabrication, description, datelimite, categorie_id, vendeur_id, nbEncheres, vendeurPseudo, vendeurId, vendeurMembreId) VALUES('sofi_article2', '1', 'DVD', 'Pathé', 'DVD', '3.00', '2002', 'description de article 32', '2006-03-12', 'cine', 'sofi_vendeur', 1, 'sofi', 'sofi_vendeur', 'sofi_membre');
INSERT INTO article (id, envente, libelle, marque, modele, prixvente, anneefabrication, description, datelimite, categorie_id, vendeur_id, nbEncheres, vendeurPseudo, vendeurId, vendeurMembreId) VALUES('sofi_article3', '1', 'T-Shirt', 'Simpsons Cie', 'XL', '10.00', '1999', 'Beau T-Shirt avec Marthe Simpson', '2006-04-03', 'vetement', 'sofi_vendeur', 1, 'sofi', 'sofi_vendeur', 'sofi_membre');
INSERT INTO article (id, envente, libelle, marque, modele, prixvente, anneefabrication, description, datelimite, categorie_id, vendeur_id, nbEncheres, vendeurPseudo, vendeurId, vendeurMembreId) VALUES('sofi_article4', '0', 'T-Shirt', 'Simpsons Cie', 'XL', '10.00', '1999', 'Beau T-Shirt avec Homer Simpson', '2006-04-04', 'vetement', 'sofi_vendeur', 0, 'sofi', 'sofi_vendeur', 'sofi_membre');

#actionEncheres
INSERT INTO actionenchere (id, date, montant, datelimite, client_id, article_id) VALUES  ('enchere1', '2006-03-02 23:09:00','3.00','2006-03-18','lotfi_client','axel_article2');
INSERT INTO actionenchere (id, date, montant, datelimite, client_id, article_id) VALUES  ('enchere2', '2006-03-05 14:45:55','330.00','2006-03-08','sofi_client','axel_article3');

INSERT INTO actionenchere (id, date, montant, datelimite, client_id, article_id) VALUES  ('enchere3', '2006-03-01 12:33:55','23100.00','2006-03-11','axel_client','lotfi_article2');
INSERT INTO actionenchere (id, date, montant, datelimite, client_id, article_id) VALUES  ('enchere4', '2006-03-04 13:45:33','23200.00','2006-03-11','sofi_client','lotfi_article2');
INSERT INTO actionenchere (id, date, montant, datelimite, client_id, article_id) VALUES  ('enchere5', '2006-03-03 17:27:22','4.00','2006-03-14','sofi_client','lotfi_article3');

INSERT INTO actionenchere (id, date, montant, datelimite, client_id, article_id) VALUES  ('enchere6', '2006-03-02 18:12:22','4.00','2006-03-12','lotfi_client','sofi_article2');
INSERT INTO actionenchere (id, date, montant, datelimite, client_id, article_id) VALUES  ('enchere7', '2006-03-02 19:45:56','11.00','2006-04-04','axel_client','sofi_article3');

