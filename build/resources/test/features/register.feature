# new feature
# Tags: optional

Feature: Registrarse en la pagina web del banco
  como un usuario del banco
  requiero registrarme con mis datos
  para poder tener una cuenta de usuario y acceder a mi informacion bancaria

  Background:
    Given El usuario se encuentra en la pagina web de registro del banco

  Scenario: Registrarse llenando los campos obligatorios y la contrasenia confirmada correctamente
    When el usuario llena los campos obligatorios del formulario y confirma
    Then el sistema muestra un mensaje de bienvenida con el nombre completo del usuario y la confirmacion de creacion de la cuenta

  Scenario: Registrarse llenando los campos obligatorios y la contrasenia confirmada incorrectamente
    When  el usuario llena los campos obligatorios del formulario con las contrasenias sin coincidir y confirma
    Then el sistema mostrara un mensaje de error