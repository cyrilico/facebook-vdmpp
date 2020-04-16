# Facebook in VDM++

Formal modelling of a Facebook-like social network in VDM++ using the Overture Tool, developed for a Software Engineering Formal Methods class (MFES) @ MIEIC, FEUP. Done with [antonioalmeida](https://github.com/antonioalmeida) and [diogotorres97](https://github.com/diogotorres97).

## Repository structure

| Folder | Foder Content |
|:-:|:-:|
| VDM++ | Facebook modelation in VDM++ |
| Java | Automatically generated code from VDM++ as well as an additional User Interface to make interaction with the Facebook model easier|

## VDM++ development

Development is thoroughly specified in our [**report**](https://github.com/cyrilico/facebook-vdmpp/blob/master/report.pdf).
Our Facebook-like social network includes users, their respective timelines (i.e., posts made by them) with different access permissions, group chats and consisting messages.

In the report, we start by identifying a formal list of requirements, as well as both Use Case and Class UML diagrams. The former can be found below.

![Use Case UML Diagram](https://i.imgur.com/0J0obUK.png)

We performed extensive testing (achieving 100% coverage), as well as both domain and invariant verifications, to ensure the reliability of the model developed.

## Java CLI

To make interaction and user testing with the model easier, Java code that implemented the model was automatically generated using the **Overture** tool. We then implemented a simple CLI that covers all main functionalities.
