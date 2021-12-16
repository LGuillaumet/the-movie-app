# The Moovie App
# Membre du groupe : Léo Guillaumet

## Objectifs fonctionnels  
Dans ce projet, nous allons créer l'application AnneFlix (The new Netflix). L'objectif est d'exploiter la base de données TheMoovieDB (https://developers.themoviedb.org/3) afin de permettre aux utilisateurs de l'application de visualiser, noter et voir la bande annonce des films de la base de données. 


## librairies à utilisées
- Navigation-fragment 
- Hilt : Injection de dépendances 
- Gson/Moshi : Sérialisation et Désérialisation JSON 
- Retrofit: Pour consommer l'API The Moovie DB
- Picasso/Glide/Coil/ : Pour afficher les images 
- OkHttp: Client HTTP

## Travail réalisé : 

- Affichage des différentes catégories de films.

- Fragment Preview qui va  récupérer une liste de films en fonction d'une catégorie de film via la route /discover de l'api TheMoovieDB.

- Fragment Popular qui va récupérer une liste de films populaires via la route /popular de l'api TheMoovieDB.

- Fragment About qui va afficher les différentes infromations liées à l'application.

- Fragment Dashboard pour afficher les différents options disponibles (uniquement popular pour le moment).

- Fragment MovieAbout qui va récupérer  des informations complémentaires sur un film grâce à son id => Par exemple l'utilisateur clique sur un film dans la liste du fragment preview on récupère son id et on va dans le fragment MovieAbout pour récupérer des informations complémentaires sur ce film.

- Affichage de la liste des films par pagination, quand on va atteindre le dernier élement de la liste on va incrémenter la page de 1 et récupérer de nouveaux films cela donne ainsi un effet d'endless scroll.

## Pouvant être amélioré :

- Le style ainsi que le thème global de l'application.

- L'ajout d'options dans le dashboard (par exemple on pourrait afficher que les films pour adultes grâce au boolean adult de l'api).

- Plus d'informations dans le Fragment MovieAbout ainsi qu'une vidéo trailer du film.

- Permettre l'ajout de films dans une liste de favoris et la récupérer dans un fragment.


