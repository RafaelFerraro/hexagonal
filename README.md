# hexagonal
Hexagonal is a project which aims to explain the core concepts of Hexagonal Architecture.

## What is Hexagonal Architecture?

Hexagonal architecture is an architectural pattern, created by Alistair Cockburn in 2005. This style is also known as Ports & Adapters architecture.

![hexagonal](https://user-images.githubusercontent.com/4781896/134986884-e3453ab7-7d5a-4c5d-a850-bd19890eb7ce.png)

The real value of this approach is that we can deliver the same application value in different ways and change the tooling and components with which our application integrates, without touching the business rules. It gives us integrity and reliability.

## Characteristics

As with every pattern, there are core ideas we have to be aware of, in order to get the real value of the pattern supply. In Hexagonal it isn't different. Here are its core ideas:

- Split the application into three parts;
  - WIP
- Isolate the business rules;
  - Simply, putting the whole business rules separately and making sure this package, module, or folder are completely isolated from the others. What I’ve seen and done in most of the examples and projects I’ve worked on, this part of the application is usually referred to as domain, a term strongly used by DDD. 
- The dependency flow MUST always point to the inner, in this case, to the domain. Therefore, the domain MUSTN’T point to nowhere else:
  - Here is where the Ports and Adapters come in. The ports are the interfaces that represent the actions and interactions of our application business logic has to do with the external world, and the adapters are, indeed, where the code that adapts to this external world comes in, the real adaptation of your application and the external world, where the external world are the toolings, other applications and things like that.

## Pros
- Isolation of business rules;
- Change flexibility;
- Postpone non crucial decisions;
- Tests in isolation - test driven architecture;

## Cons
- Complexity;
- Learning curve;
- Keep calm - you don't need interface for everything;
