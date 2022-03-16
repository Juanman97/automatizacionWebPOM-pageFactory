# new feature
# Tags: optional

Feature: Iniciar sesion en la cuenta del banco
  como un usuario del banco
  necesito iniciar sesion a mi cuenta de usuario
  para poder ver la informacion de mis cuentas bancarias

  Background:
    Given El usuario se encuentra en la pagina web del banco

  Scenario: iniciar sesion con usuario y contrasenia
    When el usuario ingresa sus credenciales de inicio de sesion y confirma
    Then el sistema mostrara la pagina de cuenta de usuario con la informacion de cuentas, balances y monto disponible

  Scenario: iniciar sesion sin usuario y contrasenia
    When el usuario confirma el inicio de sesion con los campos de usuario y contrasenia vacios
    Then el sistema mostrara un mensaje de error