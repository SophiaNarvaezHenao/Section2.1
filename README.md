

Pseudocodigo:

Clase Principal (Main)

Definir variables estáticas:
- adminUser = "Admin_001"
- adminKey = "Admin_2025"
- savingsAcct (cuenta de ahorros, inicialmente nula)
- currentAcct (cuenta corriente, inicialmente nula)

Método createAccount(tipoCuenta):
    Iniciar escáner para entrada del usuario
    Según tipoCuenta:
        Caso "1" (Ahorros):
            Imprimir "Creando cuenta de ahorros..."
            Pedir saldo inicial (doble)
            Pedir nombre del propietario
            Pedir tasa de interés (doble, sin '%')
            Crear objeto SavingsAcct con saldo, propietario e interés
            Imprimir mensaje de éxito con propietario y saldo
        Caso "2" (Corriente):
            Imprimir "Creando cuenta corriente..."
            Pedir saldo inicial (doble)
            Pedir nombre del propietario
            Pedir límite de sobregiro (doble)
            Crear objeto CurrentAcct con saldo, propietario y límite
            Imprimir mensaje de éxito con propietario y saldo
        Default:
            Imprimir "Tipo de cuenta inválido"

Método accessAccount(tipoCuenta):
    Iniciar escáner para entrada del usuario
    Inicializar contador de veces = 0
    Inicializar nombrePropietario = ""
    Según tipoCuenta:
        Caso "1" (Ahorros):
            Si savingsAcct es nula:
                Imprimir "No hay cuenta de ahorros creada"
                Retornar
            Imprimir "Accediendo a cuenta de ahorros..."
            Pedir nombre del propietario
            Si nombre está vacío:
                Imprimir "Nombre no puede estar vacío"
                Retornar
            Si nombre coincide con propietario de savingsAcct:
                Imprimir bienvenida
                Imprimir opciones: 1. Depositar / 2. Retirar / 3. Ver saldo / 4. Aplicar interés
                Leer acción
                Según acción:
                    Caso "1":
                        Pedir monto a depositar (doble)
                        Llamar deposit en savingsAcct
                    Caso "2":
                        Pedir monto a retirar (doble)
                        Llamar withdraw en savingsAcct
                    Caso "3":
                        Imprimir saldo actual
                    Caso "4":
                        Si veces >= 1:
                            Imprimir "Interés solo se aplica una vez por sesión"
                        Sino:
                            Llamar applyInterest en savingsAcct
                            Incrementar veces
                    Default:
                        Imprimir "Acción inválida"
            Sino:
                Imprimir "No se encontró cuenta con ese nombre"
        Caso "2" (Corriente):
            Si currentAcct es nula:
                Imprimir "No hay cuenta corriente creada"
                Retornar
            Imprimir "Accediendo a cuenta corriente..."
            Pedir nombre del propietario
            Si nombre está vacío:
                Imprimir "Nombre no puede estar vacío"
                Retornar
            Si nombre coincide con propietario de currentAcct:
                Imprimir bienvenida
                Imprimir opciones: 1. Depositar / 2. Retirar / 3. Ver saldo
                Leer acción
                Según acción:
                    Caso "1":
                        Pedir monto a depositar (doble)
                        Llamar deposit en currentAcct
                    Caso "2":
                        Pedir monto a retirar (doble)
                        Llamar withdraw en currentAcct
                    Caso "3":
                        Imprimir saldo actual
                    Default:
                        Imprimir "Acción inválida"
            Sino:
                Imprimir "No se encontró cuenta con ese nombre"
        Default:
            Imprimir "Tipo de cuenta inválido"

Método principal (main):
    Imprimir "Bienvenido a Sophie Bank :D"
    Iniciar escáner para entrada del usuario
    Mientras verdadero (bucle infinito hasta salida):
        Imprimir opciones: 0. Crear cuenta (solo admins) / 1. Acceder a cuenta / 2. Salir
        Leer elección
        Intentar:
            Según elección:
                Caso "0":
                    Pedir usuario admin
                    Pedir clave admin
                    Si coinciden con adminUser y adminKey:
                        Imprimir acceso concedido
                        Pedir tipo de cuenta (1. Ahorros / 2. Corriente)
                        Llamar createAccount con tipo
                    Sino:
                        Imprimir acceso denegado
                Caso "1":
                    Pedir tipo de cuenta (1. Ahorros / 2. Corriente)
                    Llamar accessAccount con tipo
                Caso "2":
                    Imprimir "Saliendo del sistema. Adiós!"
                    Cerrar escáner
                    Retornar
                Default:
                    Imprimir "Elección inválida"
        Capturar excepción:
            Imprimir "Intenta de nuevo"

Clase Abstracta Account

Definir atributos privados:
- balance (doble)
- owner (cadena)

Constructor Account(saldoInicial, propietario):
    Asignar balance = saldoInicial
    Asignar owner = propietario

Métodos abstractos:
- deposit(monto)  // Para depositar
- withdraw(monto) // Para retirar

Getters:
- getBalance(): Retornar balance
- getOwner(): Retornar owner

Setters:
- setBalance(nuevoSaldo): Asignar balance = nuevoSaldo
- setOwner(propietario): Asignar owner = propietario

Clase SavingsAcct (hereda de Account)

Atributo privado:
- Interest (doble, tasa de interés en porcentaje)

Constructor SavingsAcct(saldoInicial, propietario, interes):
    Llamar super(saldoInicial, propietario)
    Asignar Interest = interes

Sobrescribir deposit(monto):
    Si monto > 0:
        nuevoSaldo = getBalance() + monto
        setBalance(nuevoSaldo)
        Imprimir "Depositado: monto. Nuevo saldo: getBalance()"
    Sino:
        Imprimir "Monto de depósito debe ser positivo"

Sobrescribir withdraw(monto):
    Si monto > getBalance():
        Imprimir "Fondos insuficientes. Retiro denegado"
        Retornar
    Sino si monto <= 0:
        Imprimir "Monto de retiro debe ser positivo"
        Retornar
    nuevoSaldo = getBalance() - monto
    setBalance(nuevoSaldo)
    Imprimir "Retirado: monto. Nuevo saldo: getBalance()"

Método applyInterest():
    nuevoSaldo = getBalance() + (getBalance() * Interest / 100)
    setBalance(nuevoSaldo)
    Imprimir "Interés aplicado. Nuevo saldo: getBalance()"

Getter:
- getInterest(): Retornar Interest

Clase CurrentAcct (hereda de Account)

Atributo privado:
- overdraftLt (doble, límite de sobregiro)

Constructor CurrentAcct(saldoInicial, propietario, limiteSobregiro):
    Llamar super(saldoInicial, propietario)
    Asignar overdraftLt = limiteSobregiro

Sobrescribir deposit(monto):
    Si monto > 0:
        nuevoSaldo = getBalance() + monto
        setBalance(nuevoSaldo)
        Imprimir "Depositado: monto. Nuevo saldo: getBalance()"
    Sino:
        Imprimir "Monto de depósito debe ser positivo"

Sobrescribir withdraw(monto):
    Si monto > getBalance() + overdraftLt:
        Imprimir "Retiro excede límite de sobregiro. Denegado"
        Retornar
    Sino si monto <= 0:
        Imprimir "Monto de retiro debe ser positivo"
        Retornar
    Sino si monto > getBalance():
        Imprimir "Advertencia: Usando límite de sobregiro"
    nuevoSaldo = getBalance() - monto
    Imprimir "Retirado: monto. Nuevo saldo: getBalance()"

Getters y Setters:
- getOverdraftLt(): Retornar overdraftLt
- setOverdraftLt(limite): Asignar overdraftLt = limite









