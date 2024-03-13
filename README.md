# LE JEU DE LA VIE
## Backend

### Objectif

Ce backend permet de gérer la partie persistence des utilisateurs du jeu en bdd.
Il permet également le chiffrage et le déchiffrage des mots de passe.
Enfin il permet la création du serveur de socket qui est la base de la communication
via le tchat.

### Utilisation

Ce backend est développé en Java et se connecte à une base de données Postgresql.
Le port de l'application est le 8081 et le port de la socket est 1234.
Une fois connecté à la bdd et à la partie frontend, à chaque nouvelle création
d'un utilisateur, l'application va générer une clé privée et une clé publique afin
de permettre l'envoie de la clé primaire vers le front pour chiffrer le mot de passe
ainsi que le déchiffrage de ce dernier dans le back lors de l'authentification.

A noter qu'en plus de la connexion en socket, des contrôleurs REST ont été mis en place
afin de gérer les requêtes SQL.

Pour gérer les propriétés de connexion à la bdd, le fichier application.properties est à utiliser.
A noter que ce fichier peut être remplacé par un fichier .yaml selon les préférences.

### Déploiement

Cette application peut être déployée via Docker ou directement en format jar à partir du moment où la jdk21 
est installée.
Pour la partie BDD, comme précisé précédemment, il faut déployer une bdd PostGresql.