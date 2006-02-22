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
INSERT INTO client VALUES('client1');
INSERT INTO client VALUES('client2');
INSERT INTO client VALUES('client3');
INSERT INTO client VALUES('client4');
INSERT INTO client VALUES('client5');
INSERT INTO client VALUES('client6');

#vendeurs
INSERT INTO vendeur (id, nomProprioCB, numCB, dateExpirCB, codeSecuCB) VALUES('vendeur1', 'vendeur1', '11111111', '2006-12-01', '111');
INSERT INTO vendeur (id, nomProprioCB, numCB, dateExpirCB, codeSecuCB) VALUES('vendeur2', 'vendeur2', '22222222', '2006-12-01', '222');
INSERT INTO vendeur (id, nomProprioCB, numCB, dateExpirCB, codeSecuCB) VALUES('vendeur3', 'vendeur3', '33333333', '2006-12-01', '333');
INSERT INTO vendeur (id, nomProprioCB, numCB, dateExpirCB, codeSecuCB) VALUES('vendeur4', 'vendeur4', '44444444', '2006-12-01', '444');
INSERT INTO vendeur (id, nomProprioCB, numCB, dateExpirCB, codeSecuCB) VALUES('vendeur5', 'vendeur5', '55555555', '2006-12-01', '555');
INSERT INTO vendeur (id, nomProprioCB, numCB, dateExpirCB, codeSecuCB) VALUES('vendeur6', 'vendeur6', '66666666', '2006-12-01', '666');

#membres
INSERT INTO membre (id, nom, prenom, pseudo, password, email, adresse, codePostal, ville, pays, telePhoneFixe, telePhonePortable, dateNaissance, vendeur_id, client_id) VALUES('membre1', 'membre1', 'membre1', '1', '1', 'membre1@toto.com', 'adresse1', '11111', 'ville1', 'pays1', '1111111111', '1111111111', '1980-01-01', 'vendeur1', 'client1'); 
INSERT INTO membre (id, nom, prenom, pseudo, password, email, adresse, codePostal, ville, pays, telePhoneFixe, telePhonePortable, dateNaissance, vendeur_id, client_id) VALUES('membre2', 'membre2', 'membre2', '2', '2', 'membre2@toto.com', 'adresse2', '22222', 'ville2', 'pays2', '2222222222', '2222222222', '1980-01-01', 'vendeur2', 'client2'); 
INSERT INTO membre (id, nom, prenom, pseudo, password, email, adresse, codePostal, ville, pays, telePhoneFixe, telePhonePortable, dateNaissance, vendeur_id, client_id) VALUES('membre3', 'membre3', 'membre3', '3', '3', 'membre3@toto.com', 'adresse3', '33333', 'ville3', 'pays3', '3333333333', '3333333333', '1980-01-01', 'vendeur3', 'client3'); 
INSERT INTO membre (id, nom, prenom, pseudo, password, email, adresse, codePostal, ville, pays, telePhoneFixe, telePhonePortable, dateNaissance, vendeur_id, client_id) VALUES('membre4', 'membre4', 'membre4', '4', '4', 'membre4@toto.com', 'adresse4', '44444', 'ville4', 'pays4', '4444444444', '4444444444', '1980-01-01', 'vendeur4', 'client4'); 
INSERT INTO membre (id, nom, prenom, pseudo, password, email, adresse, codePostal, ville, pays, telePhoneFixe, telePhonePortable, dateNaissance, vendeur_id, client_id) VALUES('membre5', 'membre5', 'membre5', '5', '5', 'membre5@toto.com', 'adresse5', '55555', 'ville5', 'pays5', '5555555555', '5555555555', '1980-01-01', 'vendeur5', 'client5'); 
INSERT INTO membre (id, nom, prenom, pseudo, password, email, adresse, codePostal, ville, pays, telePhoneFixe, telePhonePortable, dateNaissance, vendeur_id, client_id) VALUES('membre6', 'membre6', 'membre6', '6', '6', 'membre6@toto.com', 'adresse6', '66666', 'ville6', 'pays6', '6666666666', '6666666666', '1980-01-01', 'vendeur6', 'client6'); 

#articles
INSERT INTO article (id, envente, libelle, marque, modele, prixvente, anneefabrication, description, datelimite, categorie_id, vendeur_id) VALUES('article11', '0' , 'libellé article 11', 'marque1', 'modèle1', '111.00', '1981', 'description de article 11', '2006-12-01', 'art', 'vendeur1');
INSERT INTO article (id, envente, libelle, marque, modele, prixvente, anneefabrication, description, datelimite, categorie_id, vendeur_id) VALUES('article12', '0' , 'libellé article 12', 'marque2', 'modèle2', '112.00', '1982', 'description de article 12', '2006-12-02', 'veh', 'vendeur1');
INSERT INTO article (id, envente, libelle, marque, modele, prixvente, anneefabrication, description, datelimite, categorie_id, vendeur_id) VALUES('article13', '0' , 'libellé article 13', 'marque3', 'modèle3', '113.00', '1983', 'description de article 13', '2006-12-03', 'bat', 'vendeur1');
INSERT INTO article (id, envente, libelle, marque, modele, prixvente, anneefabrication, description, datelimite, categorie_id, vendeur_id) VALUES('article14', '0' , 'libellé article 14', 'marque4', 'modèle4', '114.00', '1984', 'description de article 14', '2006-12-04', 'beau', 'vendeur1');
INSERT INTO article (id, envente, libelle, marque, modele, prixvente, anneefabrication, description, datelimite, categorie_id, vendeur_id) VALUES('article15', '0' , 'libellé article 15', 'marque5', 'modèle5', '115.00', '1985', 'description de article 15', '2006-12-05', 'beb', 'vendeur1');

INSERT INTO article (id, envente, libelle, marque, modele, prixvente, anneefabrication, description, datelimite, categorie_id, vendeur_id) VALUES('article21', '0' , 'libellé article 21', 'marque1', 'modèle1', '121.00', '1981', 'description de article 21', '2006-12-01', 'art', 'vendeur2');
INSERT INTO article (id, envente, libelle, marque, modele, prixvente, anneefabrication, description, datelimite, categorie_id, vendeur_id) VALUES('article22', '0' , 'libellé article 22', 'marque2', 'modèle2', '122.00', '1982', 'description de article 22', '2006-12-02', 'veh', 'vendeur2');
INSERT INTO article (id, envente, libelle, marque, modele, prixvente, anneefabrication, description, datelimite, categorie_id, vendeur_id) VALUES('article23', '0' , 'libellé article 23', 'marque3', 'modèle3', '123.00', '1983', 'description de article 23', '2006-12-03', 'bat', 'vendeur2');
INSERT INTO article (id, envente, libelle, marque, modele, prixvente, anneefabrication, description, datelimite, categorie_id, vendeur_id) VALUES('article24', '0' , 'libellé article 24', 'marque4', 'modèle4', '124.00', '1984', 'description de article 24', '2006-12-04', 'beau', 'vendeur2');
INSERT INTO article (id, envente, libelle, marque, modele, prixvente, anneefabrication, description, datelimite, categorie_id, vendeur_id) VALUES('article25', '0' , 'libellé article 25', 'marque5', 'modèle5', '125.00', '1985', 'description de article 25', '2006-12-05', 'beb', 'vendeur2');

INSERT INTO article (id, envente, libelle, marque, modele, prixvente, anneefabrication, description, datelimite, categorie_id, vendeur_id) VALUES('article31', '0' ,'libellé article 31', 'marque1', 'modèle1', '131.00', '1981', 'description de article 31', '2006-12-01', 'art', 'vendeur3');
INSERT INTO article (id, envente, libelle, marque, modele, prixvente, anneefabrication, description, datelimite, categorie_id, vendeur_id) VALUES('article32', '0', 'libellé article 32', 'marque2', 'modèle2', '132.00', '1982', 'description de article 32', '2006-12-02', 'veh', 'vendeur3');
INSERT INTO article (id, envente, libelle, marque, modele, prixvente, anneefabrication, description, datelimite, categorie_id, vendeur_id) VALUES('article33', '0', 'libellé article 33', 'marque3', 'modèle3', '133.00', '1983', 'description de article 33', '2006-12-03', 'bat', 'vendeur3');
INSERT INTO article (id, envente, libelle, marque, modele, prixvente, anneefabrication, description, datelimite, categorie_id, vendeur_id) VALUES('article34', '0', 'libellé article 34', 'marque4', 'modèle4', '134.00', '1984', 'description de article 34', '2006-12-04', 'beau', 'vendeur3');
INSERT INTO article (id, envente, libelle, marque, modele, prixvente, anneefabrication, description, datelimite, categorie_id, vendeur_id) VALUES('article35', '0', 'libellé article 35', 'marque5', 'modèle5', '135.00', '1985', 'description de article 35', '2006-12-05', 'beb', 'vendeur3');

INSERT INTO article (id, envente, libelle, marque, modele, prixvente, anneefabrication, description, datelimite, categorie_id, vendeur_id) VALUES('article41', '0', 'libellé article 41', 'marque1', 'modèle1', '141.00', '1981', 'description de article 41', '2006-12-01', 'art', 'vendeur4');
INSERT INTO article (id, envente, libelle, marque, modele, prixvente, anneefabrication, description, datelimite, categorie_id, vendeur_id) VALUES('article42', '0', 'libellé article 42', 'marque2', 'modèle2', '142.00', '1982', 'description de article 42', '2006-12-02', 'veh', 'vendeur4');
INSERT INTO article (id, envente, libelle, marque, modele, prixvente, anneefabrication, description, datelimite, categorie_id, vendeur_id) VALUES('article43', '0', 'libellé article 43', 'marque3', 'modèle3', '143.00', '1983', 'description de article 43', '2006-12-03', 'bat', 'vendeur4');
INSERT INTO article (id, envente, libelle, marque, modele, prixvente, anneefabrication, description, datelimite, categorie_id, vendeur_id) VALUES('article44', '0', 'libellé article 44', 'marque4', 'modèle4', '144.00', '1984', 'description de article 44', '2006-12-04', 'beau', 'vendeur4');
INSERT INTO article (id, envente, libelle, marque, modele, prixvente, anneefabrication, description, datelimite, categorie_id, vendeur_id) VALUES('article45', '0', 'libellé article 45', 'marque5', 'modèle5', '145.00', '1985', 'description de article 45', '2006-12-05', 'beb', 'vendeur4');

INSERT INTO article (id, envente, libelle, marque, modele, prixvente, anneefabrication, description, datelimite, categorie_id, vendeur_id) VALUES('article51', '0', 'libellé article 51', 'marque1', 'modèle1', '151.00', '1981', 'description de article 51', '2006-12-01', 'art', 'vendeur5');
INSERT INTO article (id, envente, libelle, marque, modele, prixvente, anneefabrication, description, datelimite, categorie_id, vendeur_id) VALUES('article52', '0', 'libellé article 52', 'marque2', 'modèle2', '152.00', '1982', 'description de article 52', '2006-12-02', 'veh', 'vendeur5');
INSERT INTO article (id, envente, libelle, marque, modele, prixvente, anneefabrication, description, datelimite, categorie_id, vendeur_id) VALUES('article53', '0', 'libellé article 53', 'marque3', 'modèle3', '153.00', '1983', 'description de article 53', '2006-12-03', 'bat', 'vendeur5');
INSERT INTO article (id, envente, libelle, marque, modele, prixvente, anneefabrication, description, datelimite, categorie_id, vendeur_id) VALUES('article54', '0', 'libellé article 54', 'marque4', 'modèle4', '154.00', '1984', 'description de article 54', '2006-12-04', 'beau', 'vendeur5');
INSERT INTO article (id, envente, libelle, marque, modele, prixvente, anneefabrication, description, datelimite, categorie_id, vendeur_id) VALUES('article55', '0', 'libellé article 55', 'marque5', 'modèle5', '155.00', '1985', 'description de article 55', '2006-12-05', 'beb', 'vendeur5');

