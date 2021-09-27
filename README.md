# hexagonal
Hexagonal is a project which aims to explain the core concepts of Hexagonal Architecture.

## What is Hexagonal Architecture?

Hexagonal architecture is an architectural pattern, created by Alistair Cockburn in 2005. This style is also known as Ports & Adapters architecture.

![hexagonal](https://user-images.githubusercontent.com/4781896/134986884-e3453ab7-7d5a-4c5d-a850-bd19890eb7ce.png)

The real value of this approach is that we can deliver the same application value in different ways and change the tooling and components with which our application integrates, without touching the business rules. It gives us integrity and reliability.

## Characteristics

As with every pattern, there are core ideas we have to be aware of, in order to get the real value of the pattern supply. In Hexagonal it isn't different. Here are its core ideas:

- Split the application down into three parts;
- Isolate the business rules (domain);
- The dependency flow MUST always point to the same direction (domain) and never let the domain parts point to another place;
