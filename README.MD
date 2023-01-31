#NUXT3 Frontend, #Springboot API, JPA, Postgres, MongoDB, DEMO. I merely abandoned #Java for a decade in favor of C# which I consider more expressive...
...and look what happened in this honey sweetpot, plenty good!


Yes I learnt OOP with Java long ago. My favorite IDE used to be Netbeans.
Today I run both IntelliJ and VSCode for my Java. I still need a couple of tricks for packaging, debugging, refactoring...

## Java language

Why I still think the C# language is better, because when I tried to code collection mappings, franckly it looked awkward with java stream and collect methods. I missed C# LINQ lambda fluency.

## Lineage, popularity

Nevertheless what makes an environment valuable is the overall framework design, ecosystem package richness, and community documentation forums. It was excellent.
I discovered a very mature backend framework with Springboot, which overcome bloated JEE design.

The sponsorship lineage of Spring is Pivotal/VMWare/EMC. It ranks popularity just next to Laravel (PHP), Flask and Django (Python). Well, in terms of language, javascript/typescript/Node.js derivatives are #1 altogether, ie. Express, Meteor, Koa, Nest, Strapi...

## DI, AOP

I could transpose smoothly my knowledge from .NetCore DI and Entity Framework, thanks to AOP with the annotation/decorator/attribute pattern, call it whatever you like...
Springboot offers loose coupling and open possibility to unit test, which I didn't toy with here. I believe JUnit and Mockito are not so different than XUnit and NSubstitute, NFluent. This is a development axis.
As I am indebted with tech/framework here, I didn't designed properly (SOLID, DDD...), you pardon me, it is also a MUST development axis as well.

## Cloud as horizon

Last but no least there's a virtualization vendor behind so Spring goes also the cloud way, there are plenty novel way to architecture a modern distributed cloud app available.

## Prerequisites

- [Docker](https://docs.docker.com/)

## Run Nuxt frontend

```
#install
yarn

#run dev server http://localhost:3000
yarn dev

#build prod
yarn build

#preview prod
yarn preview
```

## Run with JPA (PostgreSQL database)

```cmd
#run the API http://localhost:8080
#root page is the Open API Swagger

run jpa

#or just
run
```

## Run with MongoDB

```cmd
run mongodb
```

## Run all tests

```bash
gradle check
```
