@FormularioContacto
Feature: Prueba de Funcionalidad del Formulario de Contacto

  Scenario: Enviar formulario con datos válidos
    Given El usuario está en la página del formulario de contacto
    When El usuario ingresa "Juan Vanegas" en el campo "Tu Nombre:"
    And El usuario ingresa "juan.vanegas@email.com" en el campo "Tu Email:"
    And El usuario ingresa "Barrio La Cumbre" en el campo "Varrio:"
    And El usuario ingresa "Consulta General" en el campo "Asunto:"
    And El usuario ingresa "Este es un mensaje de prueba." en el campo "Mensage:"
    And El usuario hace clic en el botón "Enviar"
    Then El formulario no debe ser enviado


  Scenario: Intentar enviar formulario con campo "Tu Nombre:" corto
    Given El usuario está en la página del formulario de contacto
    When El usuario ingresa "Ana" en el campo "Tu Nombre:"
    And El usuario hace clic en el botón "Enviar"
    Then Se debe mostrar un mensaje de error indicando que El nombre debe ser mayor a 4 letras

  Scenario: Intentar enviar formulario con email inválido
    Given El usuario está en la página del formulario de contacto
    When El usuario ingresa "Jose David" en el campo "Tu Nombre:"
    And El usuario ingresa "jose.perez" en el campo "Tu Email:"
    And El usuario hace clic en el botón "Enviar"
    Then Se debe mostrar un mensaje de error indicando que El Email debe ser mayor a 4 caracteres y debe llevar @ y .

  Scenario: Navegar a Caso 2 y verificar error Not Found
    Given El usuario está en la página del formulario de contacto
    When El usuario hace clic en el botón "Caso 2"
    Then Se debe mostrar la página de error "Not Found"

Scenario: Intentar enviar formulario con "Asunto:" corto
    Given El usuario está en la página del formulario de contacto
    When El usuario ingresa "Hola" en el campo "Asunto:"
    And El usuario hace clic en el botón "Enviar"
    Then Se debe mostrar un mensaje de error indicando que El nombre debe ser mayor a 4 letras
    And El formulario no debe ser enviado