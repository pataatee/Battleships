# A31 - Bataille Navale - Rapport Rendu final

***

## 1 • Introduction
***

Ce projet a pour objectif de coder le jeu de société **Bataille Navale** en Java, tout en respectant une architecture MVC cohérente. <br>
Ainsi, nous avions déjà réfléchi à l'organisation de l'architecture du projet et soumis un **premier UML** lors du **premier rendu**. <br>
Cependant, nos choix de conception ont évolué au fur et à mesure de l'avancement du projet ; voici donc les **changements**, **améliorations** et **évolutions** de la structure du projet depuis le premier rendu. <br>

Précision concernant l'**UML** rendu : <br>
Nous rendons **3 fichiers UML** par souci de lisibilité. 
- Le premier contient **l'UML complet du projet**
- Le second ne contient que la partie **models** du projet
- Le dernier contient la partie **views & controllers** du projet

## 2 • Les classes principales
***

Voici une description des classes les plus importantes dans le projet actuel, ainsi que leurs évolutions par rapport au premier rendu.

### Package models
### Game

La classe **Game** est la classe permettant le **lancement d'une partie**, puis la **coordination** des tours des joueurs, de leurs attaques, et de la mise à jour de leurs statistiques. <br>
Son rôle n'a pas changé depuis le premier rendu ; il s'agit toujours d'une classe dont la principale responsabilité est d'orchestrer le déroulement de la partie. <br>

La **Game** contient un **GameMode**, une **énumération** contenant les différents modes de jeu possibles. <br>
Nous avons choisi d'utiliser une **énumération** plutôt qu'un simple booléen *modeÎle*, car cela nous permettrait de créer de nouveaux modes de jeu sans avoir à modifier le code existant. <br>
En effet, un booléen ne convient que pour **deux options**, et son utilisation aurait donc rendu l'extension du système plus difficile.

La **Game** contient également un **GameState**, qui est aussi une **énumération** qui quant à elle permet de vérifier si la phase de la partie en cours est la configuration de la partie, la phase de placement, partie en cours ou partie terminée. <br>
Cet attribut permettra au contrôleur **GameController** de changer de vue (d'écran) en fonction de l'état de la partie, le tout étant coordonné grâce à un observateur **GameObserver**.

### Player

La classe **Player** est, tout comme lors du premier rendu, une *classe abstraite* représentant les joueurs de la partie. <br>

Chaque joueur possède sa liste de **Boats** *(bateaux)* et sa liste de **Traps** *(pièges)*, ce qui permet d'appliquer les effets des pièges au **bon** joueur *(l'adversaire)*, et de savoir quel joueur gagne/perd *(la fin de la partie a lieu quand tous les bateaux **d'un joueur** sont coulés)*. <br>

Deux classes héritent de **Player**, **HumanPlayer** et **AIPlayer**, permettant de faire la différence entre les comportements d'un joueur humain et d'un ordinateur, notamment lors des tirs d'attaque. <br>

Une différence majeure à noter par rapport à la première version de nos choix de conception est que nous avons **supprimé le PlayerObserver**. En effet, ses fonctionnalités étaient réalisables à l'aide de **getters/setters**, et il n'était ainsi pas nécessaire d'implémenter un design pattern observateur dans ce cas-là. <br>
Nous avons cependant ajouté un **WeaponObserver**, que la classe **Weapon** implémente et que le **Player** **notifie** afin de modifier les actions de l'attaque en cas de changement d'arme. Cela permet au joueur de choisir son arme et revenir sur son choix au cours de son tour sans souci.

### Grid

Nous avons conservé pour la classe **Grid** le même fonctionnement et la même utilité que ceux appliqués lors du premier rendu. <br>
Ainsi, la grille est un tableau 2D de **Tiles** *(cases)* correspondant à la grille de chacun des joueurs. Chaque **Player** a donc une **Grid**, sur laquelle il place ses objets. <br>

Un design pattern observateur est employé pour mettre à jour la vue **CellPanel**, correspondant à l'affichage des cases de la grille, en fonction des actions ayant eu lieu sur la grille. La Grid possède donc une liste de **GridObserver**, qu'elle notifie en cas de changement. <br>
Cet observateur était déjà existant dans la première version de notre code, mais nous souhaitions implémenter un **TileObserver** en plus, qui s'est révélé être inutile.

### Placeable
Conformément à ce que nous avions déjà réalisé pour le premier rendu, la classe **Placeable** est une *classe abstraite* dont **Trap**, **Boat** et **EmptyObject** héritent. <br>
Les **Placeable** sont ainsi les objets que le joueur peut placer sur sa grille. <br>

La classe **Placeable** avait à l'origine une liste de **Tiles** occupées en tant qu'attributs, mais nous avons changé ce principe ; désormais, les **Tile** *(cases)* contiennent un **Placeable** *(Boat, Trap ou encore EmptyObject si rien n'est sur la case)*, mais les **Placeable** eux-mêmes ne connaissent pas leurs **Tiles**. <br>
Cela nous semblait plus adapté vis-à-vis du fonctionnement de notre grille, et nous a permis d'éviter des problèes liés aux dépendances entre **Placeable**, **Player** et **Grid**. <br>

Plusieurs **énumérations** ont également été ajoutées afin de récupérer facilement les **types** de chaque **Placeable** *(EmptyObject, Boat Trap)*, nous permettant d'adapter le comportement de certaines fonctions selon le type de ces objets. Une énumération **TrapType** a aussi été créée pour récupérer le type spécifique de chaque piège *(Tornade, Trou noir)*, tout en laissant la possibilité d'ajouter simplement de nouveaux types de pièges. <br>

Nous avons aussi conservé l'énumération **BoatType** qui nous permettait de connaître les tailles des différents bateaux sans avoir à les instancier. <br>

Le design pattern **Factory** demeure la méthode choisie pour instancier tous les différents types de **Placeable**, car il permet d'instancier tous ces objets sans avoir à connaître leur type tout en limitant les dépendances.

### Weapon
La classe **Weapon** est une représentation des différentes armes que le joueur pourra utiliser pour attaquer son adversaire. <br>

À l'origine, il s'agissait d'une ***interface***. Cependant, nous avons réalisé qu'une ***classe abstraite*** était plus adaptée dans cette situation, car nous souhaitions que toutes les armes partagent les mêmes attributs et qu'elles aient certaines méthodes en commun, tout en pouvant avoir un comportement différent selon le type précis de l'arme. Une *interface* ne permettait pas de **mutualiser** les attributs communs et certaines méthodes, et aurait ainsi entraîné une **redondance inutile** de code.

### Logs
Dans le premier rapport, nous avions en tête de créer une classe **Turn**, qui aurait permis de récupérer toutes les informations relatives à un tour. Or, la classe **Game** s'occupait déjà de gérer les tours des joueurs ; créer une classe supplémentaire n'était donc pas judicieux. <br>
Ainsi, pour les **Logs**, nous avons créé une classe **Log** contenant l'ID du joueur ayant réalisé une action, son nom, le type du joueur *(Humain ou IA)*, ainsi qu'une chaîne de caractères correspondant à la description de l'action réalisée. <br>

Nous avons également créé une classe **GameLogs**, contenant une liste de **Log** et des **LogsObserver**. <br>

Par conséquent, les classes **Game** et **Player** contiennent chacun un attribut **GameLogs** mis à jour en créant un nouveau **Log** à chaque action du jeu. Les **LogsObservers** sont le **LogsController** et la vue **LogsPanel**, qui vont être actualisés dès qu'un nouveau log est entré dans la liste de **Log** de **GameLogs**.

### Package controllers
Nous souhaitions à l'origine créer de nombreux contrôleurs, liés entre eux, liés grâce à des observateurs, et respectant une architecture claire. Or, nous avons vite compris que cela était plutôt incohérent, et qu'il fallait plutôt créer **un contrôleur pour chaque vue principale**, permettant ainsi le lien entre celles-ci et les modèles associés. <br>
Nous avons donc créé **6 contrôleurs**, les voici : 
- **ConfigController** : 
  - Permet de lier le modèle **ConfigData** à l'écran *(vue)* de configuration
- **GameController** : 
  - Permet de faire le lien entre le modèle **Game** et les différents écrans qu'elle doit manipuler *(la Game orchestre les tours et doit ainsi, par le biais du GameController, gérer les changements d'écrans)*
- **LogsController** : 
  - Permet de lier le modèle **GameLogs** au panel contenant les logs de la partie
- **PlacementController** : 
  - Permet de lier tous les modèles concernant le **placement des objets sur la grille** à la vue gérant ce placement
- **StatsController** : 
  - Lie le modèle **Stats** à la vue **StatsPanel**
- **WeaponController** : 
  - Permet de lier les modèles gérant la **sélection des armes par le joueur** à la vue s'en occupant

### Package views
- **Chaque écran** principal possède une **classe séparée**.
- Les **panels principaux** ont été séparés en plusieurs **"sous-panels"** dans d'autres classes, permettant la **réutilisation du code** et la **réduction de sa complexité**.
- Les vues n'implémentent **pas**, contrairement à ce que nous avions imaginé, une interface **UserScreen** ; cela n'était pas cohérent, car chaque vue affiche des choses différentes, regrouper leur code serait insensé.


## 3 • Choix de conception - Design pattern utilisés
***

Nous avons employé un certain nombre de **design pattern** pour répondre à nos besoins de conception dans le cadre de ce projet, mais nous en avons aussi supprimé quelques-uns par rapport à notre structure présentée lors du premier rendu. <br>
Voici donc les **design patterns** employés dans notre code actuel : 

### Factory

Nous utilisons le design pattern **Factory** à plusieurs reprises dans notre code. <br>
Tout comme lors du premier rendu, nous utilisons une **PlaceableFactory** permettant d'**instancier** tous les différents types de **Boat** et **Trap** *(AircraftCarrier, Cruiser, Destroyer, Submarine, TorpedoBoat, BlackHole et Tornado)* tout en gardant un code **facilement maintenable** et en **limitant les dépendances** à ces sous-classes. <br>

Nous avons choisi d'utiliser une **WeaponFactory** pour instancier les armes pour les mêmes raisons. <br>

Cependant, nous avions, lors du premier rapport, voulu créer un **ControllerFactory** pour instancier les contrôleurs créés par un autre contrôleur que nous avions appelé **ApplicationController**. Or, cet **ApplicationController** n'avait pas lieu d'exister, et nous l'avons donc supprimé. La **ControllerFactory** associée a donc également été supprimée, car il n'y a aucun intérêt à utiliser un design pattern **Factory** pour créer des contrôleurs, qui ne sont là que pour faire un lien entre les modèles et les vues.

### Observer

Le design pattern **Observer** est très utile dans notre cas pour **synchroniser les données** principalement entre le **modèle** et les **vues**. <br>
Il permet aux classes les implémentant de **réagir automatiquement** à certains **changements d'état** d'autres objets. <br>

Ainsi, comme prévu dès le premier rendu, nous avons implémenté un **GridObserver**, permettant de mettre l'affichage de la grille à jour selon l'état de ses cases *(hit, miss, island...)*, mais aussi un **GameObserver**, qui quant à lui permet de changer l'affichage des écrans selon l'état de la partie *(configuration, placement, en cours...)*. <br>

Lors du premier rendu, nous souhaitions également implémenter un **BoatObserver**, un **TileObserver**, un **PlayerObserver** ainsi qu'un **ControllerObserver**, mais nous ne les avons pas créés ; voici pourquoi : 
- **BoatObserver** : 
  - Servait à notifier le joueur lorsqu'un de ses bateaux est coulé
  - Le **Player** lui-même n'a pas besoin de le savoir directement, la **Game** s'en occupe
  - Remplacé dans les **Stats**, qui elles informent le joueur sur l'état de ses bateaux
- **TileObserver** : 
  - Servait à mettre à jour l'état des cases 
  - Peut être fait grâce à des **getters/setters** 
  - Mise à jour graphique par le biais du **GridObserver**, rajouter un **TileObserver** en plus est inutile et incorrect
- **PlayerObserver** : 
  - Servait à la mise à jour des données du joueur
  - Peut être réalisé par le biais de **getters/setters**
  - Observateur inutile dans ce cas-là
- **ControllerObserver** : 
  - Servait à changer de vue dès que nécessaire
  - Les changements d'écran ont lieu en fonction de la **Game**, rajouter un observateur supplémentaire n'est pas adapté
  - Tout se déroule grâce au **GameController**

Nous avons tout de même rajouté quelques observateurs pour que tout se déroule correctement. Les voici : 
- **WeaponObserver** : 
  - Sert à actualiser la vue **WeaponPanel** en fonction de l'arme sélectionnée par le joueur et lorsqu'une nouvelle arme est débloquée *(mode île)*
  - Le **Player** notifie la vue lorsque sa *_currentWeapon* change
  - Le **Player** notifie la vue lorsqu'une nouvelle arme est ajoutée à la liste des armes disponibles du joueur *(après sa découverte sur l'île)*
- **StatsObserver** : 
  - Sert à actualiser la vue **StatsPanel**, mettant ainsi à jour les statistiques des joueurs après chaque action
  - Les **Stats** notifient la vue dès lors qu'une nouvelle case est touchée sur la grille adverse, et permettent ainsi la mise à jour en temps réel des statistiques *(nombre de bateaux touchés, nombre de bateaux coulés, cases d'île non découvertes...)* de chaque joueur
- **LogsObserver** : 
  - Sert à actualiser la vue **LogsPanel**, mettant ainsi à jour l'historique des actions réalisées après chaque coup
  - Les **GameLogs** notifient la vue dès qu'un nouveau **Log** est créé, permettant l'actualisation de l'historique des coups joués en temps réel

### Strategy
Nous utilisons, conformément à ce que nous avions annoncé lors du premier rendu, un design pattern **Strategy** pour la sélection du **mode de placement** des objets *(Boat, Trap)*. <br>

Cela permet de conserver un code **facilement maintenable**, et permet de changer simplement le **comportement** des actions de placement selon le choix du joueur. <br>

Nous avons cependant **abandonné** l'idée de créer une stratégie pour le **mode de jeu** *(Normal/Île)*.

### State
Nous utilisions à l'origine un design pattern **State** pour gérer les changements d'état des cases de la grille. <br>
Or, ces changements d'états n'étant quasiment que visuels, utiliser ce design pattern n'était pas utile, et trop compliqué pour ce que nous en faisons. Ainsi, nous avons préféré passer par le biais d'une **énumération** **TileState**, stockée en tant qu'attribut de chaque **Tile**. Nous modifions ensuite la valeur de cet attribut en fonction de l'état de la case, et adaptons le comportement des cases en fonction de ce **TileState**.

### Énumérations
Nous utilisons de nombreuses **énumérations** dans notre code pour pouvoir adapter le comportement de certaines fonctions selon l'état d'un objet. <br>

L'utilisation des **énumérations** facilite l'ajout de nouveaux états sans modifier la logique existante du code, ce qui améliore sa maintenabilité. <br>

De plus, les **énumérations** nous permettent de manipuler des valeurs explicites, facilement compréhensibles à la lecture, ce qui rend le code plus lisible et moins sujet aux erreurs. <br>

Les **énumérations** nous permettent également de ne pas dépendre du type direct des objets utilisés, ce qui, une fois encore, améliore sa maintenabilité.

## 4 • Fonctionnalités non implémentées

Nous n'avons pas réussi à implémenter toutes les fonctionnalités demandées dans le sujet. <br>
Voici celles qui ne sont pas réalisées, et pourquoi : 
- **D1 Niveau 2** : 
  - Nous n'avons pas permis de **paramétrer la taille de la grille** dans la configuration de la partie.
  - Nous avons eu des problèmes avec les **vues** lorsque nous avons essayé de proposer cette fonctionnalité, et nous l'avons donc abandonnée par manque de temps.
  - Nous avons jugé que cette fonctionnalité n'était pas **essentielle** au bon déroulement du jeu, et qu'elle n'était pas la plus intéressante pour le joueur *(ni pour nous...)*, et nous l'avons donc abandonnée.
- **D12 Niveau 2** : 
  - Nous n'avons pas permis de placer manuellement les pièges et armes sur l'île.
  - Nous n'avons pas créé de classe regroupant ces deux objets, et il était ainsi difficile de gérer leur placement *(identique)* sans redondance de code.
  - Nous n'avions pas le temps de réorganiser notre code pour permettre l'implémentation correcte de cette fonctionnalité, donc nous l'avons abandonnée.

## 5 • Remarques/Critiques
***

- Créer une classe abstraite **Item** dont **Trap** et **Weapon** hériteraient aurait pu être utile et cohérent ; en effet, certains de leurs comportements sont similaires *(sont à usage unique, placement sur l'île...)*
- Il serait sûrement possible de mieux **mutualiser le code** en utilisant plus de ***classes abstraites***
- Il faudrait passer moins d'objets par **référence**, afin de limiter les interactions entre les classes et faciliter la maintenance du code
- Tenter de **réduire** certaines classes importantes en les séparant en plusieurs classes *(par exemple Player ou Stats)*

***

*Gauthier CLAUDEL & Manon TRANCHANT-BERTHOMIEUX* <br>
*A2 TP4*