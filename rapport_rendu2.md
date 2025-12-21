# A31 - Bataille Navale - Rapport Rendu final

** **

## 1 • Introduction

Ce projet a pour objectif de coder le jeu de société **Bataille Navale** en Java, tout en respectant une architecture MVC cohérente. <br>
Ainsi, nous avions déjà réfléchi à l'organisation de l'architecture du projet et soumis un premier UML lors du premier rendu. <br>
Cependant, nos choix de conception ont évolué au fur et à mesure de l'avancement du projet ; voici donc les changements, améliorations et évolutions de la structure du projet depuis le premier rendu. <br>

## 2 • Les classes principales

Voici une description des classes les plus importantes dans le projet actuel, ainsi que leurs évolutions par rapport au premier rendu.

### Package models
### Game

La classe Game est la classe permettant le lancement d'une partie, puis la coordination des tours des joueurs, de leurs attaques, et de la mise à jour de leurs statistiques. <br>
Son rôle n'a pas changé depuis le premier rendu ; il s'agit toujours d'une classe dont la principale responsabilité est d'orchestrer le déroulement de la partie. <br>

La Game contient un GameMode, une **énumération** contenant les différents modes de jeu possibles. <br>
Nous avons choisi d'utiliser une **énumération** plutôt qu'un simple booléen *modeÎle*, car cela nous permettrait de créer de nouveaux modes de jeu sans avoir à modifier le code existant. <br>
En effet, un booléen ne convient que pour **deux options**, et son utilisation aurait donc rendu l'extension du système plus difficile.

La Game contient également un GameState, qui est aussi une **énumération** qui quant à elle permet de vérifier si la phase de la partie en cours est la configuration de la partie, la phase de placement, partie en cours ou partie terminée. <br>
Cet attribut permettra au contrôleur GameController de changer de vue (d'écran) en fonction de l'état de la partie, le tout étant coordonné grâce à un Observateur GameObserver.

### Player

La classe Player est, tout comme lors du premier rendu, une classe abstraite représentant les joueurs de la partie. <br>
Chaque joueur possède sa liste de Boats *(bateaux)* et sa liste de Traps *(pièges)*, ce qui permet d'appliquer les effets des pièges au **bon** joueur *(l'adversaire)*, et de savoir quel joueur gagne/perd *(la fin de la partie a lieu quand tous les bateaux **d'un joueur** sont coulés)*. <br>
Deux classes héritent de Player, HumanPlayer et AIPlayer, permettant de faire la différence entre les comportements d'un joueur humain et d'un ordinateur, notamment lors des tirs d'attaque. <br>
Une différence majeure à noter par rapport à la première version de nos choix de conception est que nous avons **supprimé le PlayerObserver**. En effet, ses fonctionnalités étaient réalisables à l'aide de getters/setters, et il n'était ainsi pas nécessaire d'implémenter un design pattern observateur dans ce cas-là. <br>
Nous avons cependant ajouté un **WeaponObserver**, que la classe Weapon implémente et que le Player **notifie** afin de modifier les actions de l'attaque en cas de changement d'arme. Cela permet au joueur de choisir son arme et revenir sur son choix au cours de son tour sans souci.

### Grid

Nous avons conservé pour la classe Grid le même fonctionnement et la même utilité que ceux appliqués lors du premier rendu. <br>
Ainsi, la grille est un tableau 2D de Tiles *(cases)* correspondant à la grille de chacun des joueurs. Chaque Player a donc une Grid, sur laquelle il place ses objets. <br>
Un design pattern observateur est employé pour mettre à jour la vue CellPanel, correspondant à l'affichage des cases de la grille, en fonction des actions ayant eu lieu sur la grille. La Grid possède donc une liste de GridObserver, qu'elle notifie en cas de changement. <br>
Cet observateur était déjà existant dans la première version de notre code, mais nous souhaitions implémenter un **TileObserver** en plus, qui s'est révélé être inutile.

### Placeable
Conformément à ce que nous avions déjà réalisé pour le premier rendu, la classe Placeable est une classe abstraite dont Trap, Boat et EmptyObject héritent. <br>
Les Placeable sont ainsi les objets que le joueur peut placer sur sa grille. <br>
La classe Placeable avait à l'origine une liste de Tiles occupées en tant qu'attributs, mais nous avons changé ce principe ; désormais, les Tile *(cases)* contiennent un Placeable *(Boat, Trap ou encore EmptyObject si rien n'est sur la case)*, mais les Placeable eux-mêmes ne connaissent pas leurs Tiles. <br>
Cela nous semblait plus adapté vis-à-vis du fonctionnement de notre grille, et nous a permis d'éviter des problèes liés aux dépendances entre Placeable, Player et Grid. <br>
Plusieurs **énumérations** ont également été ajoutées afin de récupérer facilement les **types** de chaque Placeable *(EmptyObject, Boat Trap)*, nous permettant d'adapter le comportement de certaines fonctions selon le type de ces objets. Une énumération TrapType a aussi été créée pour récupérer le type spécifique de chaque piège *(Tornade, Trou noir)*, tout en laissant la possibilité d'ajouter simplement de nouveaux types de pièges. <br>
Nous avons aussi conservé l'énumération BoatType qui nous permettait de connaître les tailles des différents bateaux sans avoir à les instancier. <br>
Le design pattern **Factory** demeure la méthode choisie pour instancier tous les différents types de Placeable, car il permet d'instancier tous ces objets sans avoir à connaître leur type tout en limitant les dépendances.

### Weapon
La classe Weapon est une représentation des différentes armes que le joueur pourra utiliser pour attaquer son adversaire. <br>
À l'origine, il s'agissait d'une **interface**. Cependant, nous avons réalisé qu'une **classe abstraite** était plus adaptée dans cette situation, car nous souhaitions que toutes les armes partagent les mêmes attributs et qu'elles aient certaines méthodes en commun, tout en pouvant avoir un comportement différent selon le type précis de l'arme. Une interface ne permettait pas de mutualiser les attributs communs et certaines méthodes, et aurait ainsi entraîné une redondance inutile de code.

### Logs
Dans le premier rapport, nous avions en tête de créer une classe Turn, qui aurait permis de récupérer toutes les informations relatives à un tour. Or, la classe Game s'occupait déjà de gérer les tours des joueurs ; créer une classe supplémentaire n'était donc pas judicieux. <br>
Ainsi, pour les Logs, nous avons créé une classe Log contenant l'ID du joueur ayant réalisé une action, son nom, le type du joueur *(Humain ou IA)*, ainsi qu'une chaîne de caractères correspondant à la description de l'action réalisée. <br>
Nous avons également créé une classe GameLogs, contenant une liste de Log et des LogsObserver. <br>
Par conséquent, les classes Game et Player contiennent chacun un attribut GameLogs mis à jour en créant un nouveau Log à chaque action du jeu. Les LogsObservers sont le LogsController et la vue LogsPanel, qui vont être actualisés dès qu'un nouveau log est entré dans la liste de Log de GameLogs.


## 

## deroulement d'une partie avec quels appels a quelles classes etc

## choix conception
beaucoup d'enums car maintenable, expansible etc