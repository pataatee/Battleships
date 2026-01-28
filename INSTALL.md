# ⚙️ Installation de l'application

## 🛈 Prérequis

- **Java 21** doit être installé sur votre machine

## 🛠️ Comment installer ?

### Avec GIT
- **Clonez** ce dépôt GIT sur votre machine : 

*Avec SSH :*
```bash
git clone git@git.unistra.fr:tranchantberthomieux-claudel/a31-bataille-navale.git
```

*Avec HTTPS :*
```bash
git clone https://git.unistra.fr/tranchantberthomieux-claudel/a31-bataille-navale.git
```
### Sans GIT
- Allez sur **[ce site (GitHub)](https://github.com/pataatee/Battleships/tree/main)**
- Cliquez sur **Code** *(bouton vert en haut à droite de la page du projet)* > Téléchargez le `.zip`
- Dézippez le dossier *(`unzip a31-bataille-navale-main.zip` sous Linux)*

<br>

### Sans IDE compatible avec le Java
- **Déplacez-vous** dans le dossier `a31-bataille-navale/BatailleNavale`
- **Compilez** le projet : `javac -d bin -sourcepath src src/Main.java src/**/*.java`
- **Exécutez** le projet : `java -cp bin:src Main`

### Avec IDE compatible avec le Java *(IntelliJ)*
- **Ouvrez** le projet `a31-bataille-navale/BatailleNavale`
- **Naviguez** vers `/src/Main.java`
- **Clic droit** sur `Main.java`, puis cliquez sur **Run**. Votre IDE ouvrira ensuite une fenêtre correspondant à l'interface graphique du jeu !


### 🎉 Vous avez installé le jeu avec succès !
Maintenant, amusez-vous bien ! 😉