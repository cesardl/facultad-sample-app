# Facultad Sample Application [![Build Status](https://travis-ci.org/cesardl/facultad-sample-app.svg?branch=master)](https://travis-ci.org/cesardl/facultad-sample-app) [![Maintainability](https://api.codeclimate.com/v1/badges/07262732998e04a64027/maintainability)](https://codeclimate.com/github/cesardl/facultad-sample-app/maintainability) [![Test Coverage](https://api.codeclimate.com/v1/badges/07262732998e04a64027/test_coverage)](https://codeclimate.com/github/cesardl/facultad-sample-app/test_coverage)

El siguiente es una aplicacion en el cual se ve el uso de las distintas query SQL para el SGBD MySQL.

## v 2.0.0
Ya con el pasar de los años me animé a hacer la actualización a Java 8 y utilizar Spring Framework. Además estoy considerando la creación de test unitarios que en su momento no hice.

## v 1.0.0
Esta aplicacion la hice cuando estaba en 3er año en la UNMSM, la estoy actualizando para que tenga funcionalidades de Java 7, otras librerias graficas y la implementacion de algunos patrones de diseño de software.

## Troubleshooting

Al realizar la configuraci&oacute;n en travis tuve un problemilla ya que la clase `javax.swing.JDialog` necesita la variable X11 configurada para poder levantar su dependencia gr&aacute;fica.

Aqu&iacute; explican como simular un entorno gr&aacute;fico en Travis.

```
https://docs.travis-ci.com/user/gui-and-headless-browsers/#Using-xvfb-directly
```
