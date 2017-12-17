# Bibliothèque JEE

## Description

### À propos

Application web de gestion d'une bibliothèque. À l'opposé de la première, dans cette seconde version, plusieurs clients peuvent consulter et se connecter en même temps au site web.

### Limites

Le site n'est pas tout à fait fonctionnel : malgré plusieurs heures de recherche, l'injection des EJBs avec la clause `@EJB` ne fonctionne pas pour une raison inconnue (il semblerait que les EJB ne fassent pas partie du contexte de la "servlet".). Ayant attaqué la suite du projet assez tard, nous n'avons pas eu le temps de contacter M. Busca pour obtenir une assistance.

En revanche, dans la partie "fonctionnement" de ce document, sont listés les points importants que nous avons pu développer.

### Fonctionnement

* Le modèle a été modifié en "singleton pattern" : les trois classes du modèle sont des EJB "singleton" et ne sont donc instanciées qu'une unique fois au lancement du serveur (clause `@Startup`).
* Les classes du modèle gèrent respectivement une collection d'utilisateurs, de livres et d'opérations (emprunts ou réservations).
* Des données exemples sont introduites dans les collections après l'instanciation des classes avec la clause `@PostConstruc`, afin de rendre les tests de la plateforme possibles.

## Installation

Pour récupérer le projet, vous pouvez le cloner avec la commande suivante.

```
git clone https://github.com/RomainBat/BibliothequeJEE.git
```

Une fois en place sur un serveur JEE, rendez-vous sur http://localhost:[port]/BibliothequeJEE/Site

## Utilisation

L'application s'utilise en mode déconnecté avec la recherche de livre. Il faut se connecter pour avoir accès à d'autres fonctionnalités.

Pour se connecter, utiliser les combinaisons identifiant | mot de passe suivants :
* **Biblitohécaire** : Admin | root
* **Adhérent** : Bob92 | user

## Auteurs

* **Auxane Thouary**
* **Romain Baticle**

## Sources

* Cours de M. Busca sur la programmation web avec JEE
