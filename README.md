Proiect sgbdVerificationIsNullJava

Normalizarea constrangerilor de existenta
• un tabel trebuie sa aiba cel putin o coloana (un atribut), in afara de cheia primara, care sa NU
admita valori null.
• implementare: se scaneaza fiecare tabel din baza de date, iar daca nu se gasesc coloane cu
constrangerea de existenta, se cauta coloane in care nu sunt NULL-uri in acel moment. Daca nu
exista nicio coloana de acest gen, trebuie informat utilizatorul ca acel tabel este gresit proiectat
(se pot introduce linii numai cu null-uri) si rugat sa elimine null-uri macar dintr-o coloana. Altfel,
se da utilizatorului lista de coloane pe care se poate pune constrangerea not null si rugat sa
aleaga coloana la care sa se adauge in mod automat aceasta constrangere.
