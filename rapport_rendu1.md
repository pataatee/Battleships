# A31 - Bataille Navale - Rapport rendu 1

** **

## 1 • Les classes pricipales

Voici une description des principales classes créées pour le projet.

### Package models
### Player
La classe **<font color='7915A1'>Player</font>** correspond aux différents **joueurs** de la partie. <br>
Il s'agit d'une **classe abstraite**, ce qui permet de pouvoir créer les différents types de joueurs *(IA et Humain)*.

Un **<font color='C72646'>observeur</font>** observe le **<font color='7915A1'>Player</font>** pour notifier les classes nécessaires lors de la **fin de son tour**.
<br>

### Game
La **<font color='7915A1'>Game</font>** *(partie)* est une classe coordonnant les **tours** des joueurs.
Elle donne le tour au joueur au bon moment et attend que ce dernier ait terminé son tour pour passer au tour suivant. <br>
La classe **<font color='7915A1'>Game</font>** utilise également la classe **<font color='7915A1'>ConfigData</font>** pour configurer les **paramètres** de la partie via l'écran de configuration **<font color='2C8A56'>ConfigScreen</font>** (qui lui-même utilise donc un contrôleur **<font color='198F94'>ConfigController</font>**).
<br>

### Placeables
La classe **<font color='7915A1'>Placeables</font>** est une classe abstraite rassemblant **tous les objets** que l'on peut **placer** sur la grille. Cela inclut donc les différents types de bateaux et pièges. <br>
Elle possède en tant qu'attributs la **taille** de l'objet plaçable et la **liste des cases** ***<font color='7915A1'>(Tile)</font>*** que l'objet occupe. <br> <br>
Les classes abstraites **<font color='7915A1'>Boat</font>** et **<font color='7915A1'>Trap</font>** *(bateaux et pièges)* étendent cette classe afin de pouvoir **différencier les comportements** des bateaux et des pièges. <br> 
Des classes encore plus spécifiques étendent ensuite les classes **<font color='7915A1'>Boat</font>** *(TorpedoBoat, Destroyer, AircraftCarrier, Submarine et Cruiser)* et **<font color='7915A1'>Trap</font>** *(BlackHole, Tornado)* pour pouvoir gérer les différences de comportement de **chaque bateau** et de **chaque piège**. <br>
Les bateaux utilisent une énumération **<font color='7915A1'>BoatType</font>** permettant de connaître la **taille** des bateaux **sans avoir à les instancier**. <br> <br>
Cette architecture nous permet également d'intégrer très simplement au jeu de nouveaux types bateaux ou pièges. <br> <br>
Afin de créer les différents pièges **<font color='7915A1'>*(Trap)*</font>** et bateaux ***<font color='7915A1'>(Boat)</font>***, nous utilisons un **design pattern <font color='C72646'>Factory</font>**.
Cela nous permet de gérer le placement de tous ces éléments de la même manière, sans se soucier du type précis d'objet dont il s'agit (car ce n'est pas pertinent pour le placement sur la grille).
<br>

### Grid 
La classe **<font color='7915A1'>Grid</font>** correspond à la **grille** des joueurs.
Elle est constituée d'une liste 2D de **cases** ***<font color='7915A1'>(Tile)</font>***. <br>
**Chaque joueur possède une grille** sur laquelle il positionne ses bateaux et pièges.
<br>

### Tile 
Les **<font color='7915A1'>Tiles</font>** sont donc les cases de la **<font color='7915A1'>Grid</font>**.
Elles ont une **position** sur la grille *(coordonnées x,y)* ainsi qu'un ***état <font color='7915A1'>(State)</font>***. <br>
Cet état correspond à **ce qui est sur la case** *(bateau, piège, île, vide)*, et permet aussi de savoir si cette case a été **touchée** par l'ennemi ou non. <br> <br>
Cette organisation nous permet de gérer plus facilement le **comportement** des cases lorsqu'elles se font toucher par l'adversaire, et nous permettra également de **faire la distinction** entre les différentes cases plus facilement lors de l'affichage.
<br>

### Weapons
Les **<font color='7915A1'>Weapons</font>** correspondent aux différentes **armes** que le joueur pourra utiliser pour attaquer son adversaire. <br> <br>
Il s'agit ainsi d'une **interface**, que les classes concrètes **<font color='7915A1'>Missile</font>**, **<font color='7915A1'>Bomber</font>** et **<font color='7915A1'>Sonar</font>** implémentent. <br>
L'utilisation d'une arme retourne une **liste d'effets** **<font color='7915A1'>*(Effect)*</font>**, qui gère le **comportement** de l'arme utilisée sur la grille adverse. Ces effets contiennent les **cases à toucher** ainsi que les **dégâts** de l'arme. <br>
Une **énumération <font color='7915A1'>EffectType</font>** est utilisée pour gérer les **différents types d'effets** *(toucher, scanner...)*. <br> <br>
Pour créer les différents types d'armes, une **<font color='C72646'>Factory</font>** est employée.
<br>

### Logs
La classe **<font color='7915A1'>Log</font>** nous permet de conserver un **historique des coups joués** lors de la partie. <br>
Chaque entrée dans les logs correspond à un tour de la partie **<font color='7915A1'>*(Turn)*</font>**. <br> <br>
Chaque joueur possède des **statistiques <font color='7915A1'>*(Stats)*</font>**, qui elles-mêmes contiennent les **informations du dernier tour** joué. <br>
Cela permet d'afficher par la suite sur le **<font color='2C8A56'>MainScreen</font>** *(écran principal de la partie)* le **dernier coup** joué par le joueur, l'**historique des coups joués**, ainsi qu'un **résumé des statistiques** de la partie sur l'écran de fin **<font color='2C8A56'>EndGameScreen</font>**.
<br>

### Package views
- Chaque écran parle à **un** contrôleur, qui lui-même parle à différents modèles pour transmettre à la vue les informations nécessaires.
- Plusieurs **<font color='C72646'>observers</font>** sont implémentés pour **coordonner l'affichage des différents écrans** en fonction des changements dans les modèles.
- **Une classe** correspond à **un écran** spécifique. Toutes les classes concrètes du package view implémentent une interface **<font color='2C8A56'>UserScreen</font>**.

### Package controller
- Toutes les classes controllers étendent un contrôleur de base, **<font color='198F94'>BaseController</font>** *(classe abstraite)*, qui notifie le contrôleur principal de l'application, **<font color='198F94'>ApplicationController</font>**, lorsqu'un **changement d'écran** doit avoir lieu. Cette information peut être transmise grâce à un **<font color='C72646'>observer</font> <font color='198F94'>*(ControllerObserver)*</font>**.
- La classe **<font color='198F94'>ApplicationController</font>** est celle qui **instancie** tous les différents ***modèles/vues/contrôleurs*** de l'application : il s'agit du **point d'entrée** de celle-ci.

** **

## 2 • Les design patterns utilisés
### Factory
Plusieurs **<font color='C72646'>Factory</font>** sont utilisées dans l'application pour créer les classes concrètes héritant d'une même classe abstraite. <br>
Nous en utilisons ainsi pour la **création des objets plaçables** *(bateaux et pièges)*, pour **créer les différents types d'armes**, mais aussi lors de la **création des différents contrôleurs** par **<font color='198F94'>ApplicationController</font>**. <br> <br>
Cela permet de **limiter les dépendances** avec les classes externes, qui n'auront accès qu'à la **<font color='C72646'>Factory</font>** et à la classe abstraite, plutôt qu'à chacune des classes concrètes ainsi créées.

### Observer
Plusieurs **<font color='C72646'>Observers</font>** sont utilisés dans l'application pour permettre à certaines classes de **réagir à un changement** dans une autre classe. <br> <br>
Des **<font color='C72646'>observers</font>** sont ainsi implémentés pour la **grille** ***<font color='7915A1'>(Grid)</font>*** et les **cases** ***<font color='7915A1'>(Tile)</font>*** ***<font color='7915A1'>(GridObserver</font>** et **<font color='7915A1'>TileObserver)</font>***, permettant la **mise à jour de l'état des cases** de la grille (et la mise à jour visuelle de ce changement). <br>
Un autre **<font color='C72646'>observer</font>** est employé pour **mettre à jour** des données concernant le **joueur** ***<font color='7915A1'>(PlayerObserver)</font>***. <br>
Un **<font color='C72646'>observer</font>** **<font color='7915A1'>BoatObserver</font>** permet de **notifier** le joueur lorsqu'un de ses bateaux est **coulé**. <br>
**<font color='7915A1'>GameObserver</font>** est un autre **<font color='C72646'>observer</font>** qui quant à lui permet d'émettre une notification lors des **changements de tours** et de mettre à jour l'**index du joueur courant**. <br>
Un **<font color='C72646'>observer</font>** **<font color='198F94'>ControllerObserver</font>** permet de **changer de vue** dès que nécessaire.

### Strategy
Le **design pattern <font color='C72646'>Strategy</font>** est utilisé plusieurs fois pour permettre au joueur d'explorer différentes manières de jouer. <br>
Une **<font color='C72646'>Strategy</font>** est par exemple utilisée pour permettre le **choix du mode de jeu** *(normal/île)*, ou encore pour laisser le joueur choisir le **mode de placement** de ses bateaux *(aléatoire/manuel)*.

### State
Le **design pattern <font color='C72646'>State</font>** permet de modifier le comportement d'un objet lorsque son état change. <br> <br>
Nous utilisons ainsi ce **design pattern** lors du **changement d'état des cases** de la grille, par exemple lorsqu'une case de bateau se fait toucher *(la case passe de l'état **<font color='7915A1'>BoatTile</font>** à **<font color='7915A1'>BoatHitTile</font>**)*. <br>
Cela permettra de mieux gérer les **différences d'état** des cases sans pour autant les faire changer de classe (toutes les cases resteront de type **<font color='7915A1'>Tile</font>**).


** **


## 3 • Remarques/Critiques

- La classe **<font color='7915A1'>Player</font>** possède beaucoup de dépendances, elle a un rôle non négligeable dans la logique du jeu. 
Cela apporte néanmoins quelques avantages :
  - Il serait facile d'implémenter une partie jouable à **plus de 2 joueurs**, car le déroulement de la partie dépend des joueurs et non pas de la partie elle-même *(code principal dans Player et non pas dans **<font color='7915A1'>Game</font>**)*
  - La partie ***<font color='7915A1'>(Game)</font>*** réagit selon le joueur ***<font color='7915A1'>(Player)</font>***, ce qui rejoint le point précédent.
- Nous utilisons beaucoup d'**<font color='C72646'>observers</font>**, mais cela est nécessaire pour faire de l'"événementiel" et donc faire **réagir** des classes à des **changements** en continu.
- Le contrôleur **<font color='198F94'>ApplicationController</font>** gère beaucoup de choses, mais qui sont uniquement l'**instanciation** de différentes classes ; cela n'est donc pas un problème majeur.
<br>
<br>
** **
*Gauthier CLAUDEL & Manon TRANCHANT-BERTHOMIEUX*

[//]: # (7915A1: Models)
[//]: # (2C8A56: Views)
[//]: # (198F94: Controllers)
[//]: # (C72646: Design Patterns)