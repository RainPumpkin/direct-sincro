Insert into Pessoa VALUES('Manuel Silva', '910999888', 'L-8574938', 'manueltotalreal@gmail.com');
Insert into Pessoa VALUES('Antonio Eusebio', '257875316', 'L-7381577', 'anteus@isel.pt');
Insert into Pessoa VALUES('Daniel Azevedo', '753164296', 'L-9632581', 'danaze@isel.pt');

Insert into Subscritor Values('910999888', 'Lyoko');
Insert into Subscritor Values('257875316', 'Eusebio');

Insert into Admin Values('910999888', 'RainPumpkin7266', 'CodeXana');

Insert into Veiculo Values('WN-23-DA', 'Ford', 'nsei', '910999888');
Insert into Veiculo Values('10-AG-AG', 'Seat', 'nsei', '910999888');
Insert into Veiculo Values('KL-38-FG', 'Volkswagen', 'nsei', '910999888');
Insert into Veiculo Values('FA-AF-90', 'Tesla', 'nsei', '910999888');

Select * from subscritor;
select * from veiculo;
select * from evento_transito;
delete from veiculo where matricula = '01-98-TY';