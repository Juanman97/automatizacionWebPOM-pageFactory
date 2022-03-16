# new feature
# Tags: optional

Feature: Contactarme con la empresa a traves de la seccion Contact Us
  como un usuario de la pagina
  necesito enviar un mensaje a la empresa con mis datos
  para poder comunicarme con ellos y expresar mis inquietudes

  Background:
    Given el usuario se encuentra en la pagina web de Contact Us

  Scenario: Envio de mensaje con todos los datos del formulario
    When el usuario ingresa todos los campos del formulario y confirma
    Then el sistema mostrara en pantalla un mensaje de contacto exitoso

  Scenario: Envio de mensaje sin ningun dato del formulario
    When el usuario no ingresa ningun campo del formulario y confirma
    Then el sistema no enviara el mensaje y mostrara los campos requeridos