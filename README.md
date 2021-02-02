Proiect sgbdVerificationIsNullJava

Normalizare chei primare
• Pentru fiecare tabela in care nu este cheie primara, se adauga una surogat.
• Pentru fiecare tabela in care
- exista o cheie primara pe o singura coloana dar care nu este numerica
- exista o cheie primara numerica pe mai mai multe atribute (cel putin 2)
creeaza o cheie primara standard surogat cu autonumarare dupa ce o decade pe cea existenta din
aceasta postura, ceea ce inseamna si stergerea cheilor straine care o refera si refacerea lor catre noua
cheie surogat.
