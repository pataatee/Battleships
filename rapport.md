# Rapport

## Les classes pricipales
### Package models
### Player :
La classe Player correspond aux différents joueurs de la partie. 
Il s'agit d'une classe abstraite, nous permettant de pouvoir créer les différents types de joueurs (IA et Humain).

Un **observeur** observe le Player pour notifier les classes nécessaires lors de la **fin de son tour**.

### Game : 
La Game (partie) est une classe coordonnant les tours des joueurs.
Elle donne le tour au joueur au bon moment et attend que ce dernier ait terminé son tour pour passer au tour suivant.
La classe Game utilise également la classe **<font color='purple'>ConfigData</font>** pour configurer les paramètres de la partie via l'écran de configuration ConfigScreen (qui lui-même utilise donc un contrôleur ConfigController).

### Placeables :
La classe Placeables est une classe abstraite rassemblant tous les objets que l'on peut placer sur la grille. Cela inclut donc les différents types de bateaux et pièges.
Elle possède en tant qu'attributs la taille de l'objet plaçable et la liste des cases (Tile) que l'objet occupe.
Les classes abstraites Boat et Trap (bateaux et pièges) étendent cette classe afin de pouvoir différencier les comportements des bateaux et des pièges.
Des classes encore plus spécifiques étendent ensuite les classes Boat (TorpedoBoat, Destroyer, AircraftCarrier, Submarine et Cruiser) et Trap (BlackHole, Tornado) pour pouvoir gérer les différences de comportement de chaque bateau et de chaque piège.
Les bateaux utilisent une énumération BoatType permettant de connaître la taille des bateaux sans avoir à les instancier.
Cette architecture nous permet également d'intégrer très simplement au jeu de nouveaux types bateaux ou pièges.
Afin de créer les différents pièges (Trap) et bateaux (Boat), nous utilisons un design pattern Factory.
Cela nous permet de gérer le placement de tous ces éléments de la même manière, sans se soucier du type précis d'objet dont il s'agit (car ce n'est pas pertinent pour le placement sur la grille).


### Grid : 
La classe Grid correspond à la grille des joueurs.
Elle est constituée d'une liste 2D de cases (Tile).
Chaque joueur possède une grille sur laquelle il positionne ses bateaux et pièges.

### Tile : 
Les Tiles sont donc les cases de la Grid.
Elles ont une position sur la grille (coordonnées x,y) ainsi qu'un état (State).
Cet état correspond à ce qui est sur la case (bateau, piège, île, vide), et permet aussi de savoir si cette case a été touchée par l'ennemi ou non.
Cette organisation nous permet de gérer plus facilement le comportement des cases lorsqu'elles se font toucher par l'adversaire, et nous permettra également de faire la distinction entre les différentes cases plus facilement lors de l'affichage.

### Weapons : 
Les Weapons correspondent aux différentes armes que le joueur pourra utiliser pour attaquer son adversaire.
Il s'agit ainsi d'une interface, que les classes concrètes Missile, Bomber et Sonar implémentent.
L'utilisation d'une arme retourne une liste d'effets (Effect), qui gère le comportement de l'arme utilisée sur la grille adverse. Ces effets contiennent les cases à toucher ainsi que les dégâts de l'arme.
Une énumération EffectType est utilisée pour gérer les différents types d'effets (toucher, scanner...).
Pour créer les différents types d'armes, une Factory est employée.

### Logs : 
La classe Log nous permet de conserver un historique des coups joués lors de la partie.
Chaque entrée dans les logs correspond à un tour de la partie (Turn).
Chaque joueur possède des statistiques (Stats), qui elles-mêmes contiennent les informations du dernier tour joué.
Cela permet d'afficher par la suite sur le MainScreen (écran principal de la partie) le dernier coup joué par le joueur, l'historique des coups joués, ainsi qu'un résumé des statistiques de la partie sur l'écran de fin EndGameScreen.

### Package views
- Chaque écran parle à un contrôleur, qui lui-même parle à différents modèles pour transmettre à la vue les informations nécessaires.
- Plusieurs observers sont implémentés pour coordonner l'affichage des différents écrans en fonction des changements dans les modèles.
- Une classe correspond à un écran spécifique. Toutes les classes concrètes du package view implémentent une interface UserScreen.

### Package controller
- Toutes les classes controllers étendent un contrôleur de base, BaseController (classe abstraite), qui notifie le contrôleur principal de l'application, ApplicationController, lorsqu'un changement d'écran doit avoir lieu. Cette information peut être transmise grâce à un observer (ControllerObserver).
- La classe ApplicationController est celle qui instancie tous les différents modèles/vues/contrôleurs de l'application : il s'agit du point d'entrée de celle-ci.

## Les design patterns utilisés
### Factory
Plusieurs Factory sont utilisées dans l'application pour créer les classes concrètes héritant d'une même classe abstraite.
Nous en utilisons ainsi pour la création des objets plaçables (bateaux et pièges), pour créer les différents types d'armes, mais aussi lors de la création des différents contrôleurs par ApplicationController.
Cela permet de limiter les dépendances avec les classes externes, qui n'auront accès qu'à la Factory et à la classe abstraite, plutôt qu'à chacune des classes concrètes ainsi créées.

### Observer
Plusieurs Observers sont utilisés dans l'application pour permettre à certaines classes de réagir à un changement dans une autre classe.
Des observers sont ainsi implémentés pour la grille (Grid) et les cases (Tile) (GridObserver et TileObserver), permettant la mise à jour de l'état des cases de la grille (et la mise à jour visuelle de ce changement).
Un autre observer est employé pour mettre à jour des données concernant le joueur (PlayerObserver).
Un observer BoatObserver permet de notifier le joueur lorsqu'un de ses bateaux est coulé.
GameObserver est un autre observer qui quant à lui permet d'émettre une notification lors des changements de tours et de mettre à jour l'index du joueur courant.
Un observer ControllerObserver permet de changer de vue dès que nécessaire.

### Strategy
Le design pattern Strategy est utilisé plusieurs fois pour permettre au joueur d'explorer différentes manières de jouer.
Une Strategy est par exemple utilisée pour permettre le choix du mode de jeu (normal/île), ou encore pour laisser le joueur choisir le mode de placement de ses bateaux (aléatoire/manuel).

### State
Le design pattern State permet de modifier le comportement d'un objet lorsque son état change.
Nous utilisons ainsi ce design pattern lors du changement d'état des cases de la grille, par exemple lorsqu'une case de bateau se fait toucher (la case passe de l'état BoatTile à BoatHitTile).
Cela permettra de mieux gérer les différences d'état des cases sans pour autant les faire changer de classe (toutes les cases resteront de type Tile).

## Remarques/Critiques

- La classe Player possède beaucoup de dépendances, elle a un rôle non négligeable dans la logique du jeu. 
Cela apporte néanmoins quelques avantages :
  - Il serait facile d'implémenter une partie jouable à plus de 2 joueurs, car le déroulement de la partie dépend des joueurs et non pas de la partie elle-même (code principal dans Player et non pas dans Game)
  - La partie (Game) réagit selon le joueur (Player), ce qui rejoint le point précédent.
- Nous utilisons beaucoup d'observers, mais cela est nécessaire pour faire de l'"événementiel" et donc faire réagir des classes à des changements en continu.
- Le contrôleur ApplicationController gère beaucoup de choses, mais qui sont uniquement l'instanciation de différentes classes ; cela n'est donc pas un problème majeur.




