Insert into Cidadao VALUES('Manuel Silva', '910999888', 'L-8574938', 'manueltotalreal@gmail.com', 'Lyoko');
Insert into Cidadao VALUES('Antonio Eusebio', '257875316', 'L-7381577', 'anteus@isel.pt', 'Eusebio');
Insert into Cidadao VALUES('Daniel Azevedo', '753164296', 'L-9632581', 'danaze@isel.pt', 'Password12345');

Insert into Subscritor Values('910999888');
Insert into Subscritor Values('257875316');

Insert into Veiculo Values('WN-23-DA', 'Ford', 'nsei', '910999888');
Insert into Veiculo Values('10-AG-AG', 'Seat', 'nsei', '910999888');
Insert into Veiculo Values('KL-38-FG', 'Volkswagen', 'nsei', '910999888');
Insert into Veiculo Values('FA-AF-90', 'Tesla', 'nsei', '910999888');

Select * from Subscritor;
select * from Veiculo;
select * from Contraordenacao;
delete from Veiculo where matricula = '01-98-TY';