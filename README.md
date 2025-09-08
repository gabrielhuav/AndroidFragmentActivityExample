# 📱 Android Fragment & Activity Navigation Example

Una aplicación Android de ejemplo que demuestra cómo implementar navegación entre **Fragments** y **Activities**, incluyendo el paso de datos entre componentes.

## 📋 Descripción del Proyecto

Esta aplicación educativa está diseñada para enseñar los conceptos fundamentales de:
- Navegación entre Fragments
- Navegación entre Activities  
- Paso de datos entre componentes
- Manejo del ciclo de vida
- Uso de View Binding
- Implementación de transiciones

## 🏗️ Arquitectura del Proyecto

```
MainActivity
├── WelcomeFragment (Fragment principal)
│   ├── Formulario de entrada
│   ├── Botón → SecondFragment
│   └── Botón → SecondActivity
├── SecondFragment (Fragment overlay)
│   ├── Contador interactivo
│   └── Botón para cerrar
└── SecondActivity (Activity independiente)
    ├── Muestra datos recibidos
    └── Botones de navegación
```

## 📁 Estructura de Archivos

### 🎯 Activities
- **`MainActivity.kt`** - Activity principal que aloja los fragments
- **`SecondActivity.kt`** - Activity secundaria para demostrar navegación entre activities

### 🧩 Fragments
- **`WelcomeFragment.kt`** - Fragment de bienvenida con formulario interactivo
- **`SecondFragment.kt`** - Fragment con contador que se superpone al anterior

### 🎨 Layouts
- **`activity_main.xml`** - Layout principal con container para fragments
- **`activity_second.xml`** - Layout de la segunda activity
- **`fragment_welcome.xml`** - Layout del fragment de bienvenida
- **`fragment_second.xml`** - Layout del fragment contador

### 📝 Resources
- **`strings.xml`** - Recursos de strings para internacionalización

## ✨ Características Principales

### 🔄 Navegación entre Fragments

1. **Fragment Transaction con BackStack**
   ```kotlin
   parentFragmentManager.beginTransaction()
       .add(R.id.fragmentContainer, secondFragment)
       .addToBackStack("SecondFragment")
       .commit()
   ```

2. **Manejo del botón atrás**
   ```kotlin
   parentFragmentManager.popBackStack()
   ```

### 🚀 Navegación entre Activities

1. **Intent con datos extras**
   ```kotlin
   val intent = Intent(this, SecondActivity::class.java).apply {
       putExtra("NOMBRE_USUARIO", nombreUsuario)
       putExtra("CONTADOR", 42)
       putExtra("ES_VIP", true)
   }
   startActivity(intent)
   ```

2. **Recepción de datos**
   ```kotlin
   val nombreUsuario = intent.getStringExtra("NOMBRE_USUARIO")
   val numeroContador = intent.getIntExtra("CONTADOR", 0)
   val esVip = intent.getBooleanExtra("ES_VIP", false)
   ```

### 🎨 Animaciones de Transición

```kotlin
requireActivity().overridePendingTransition(
    android.R.anim.slide_in_left,
    android.R.anim.slide_out_right
)
```

## 🛠️ Tecnologías Utilizadas

- **Kotlin** - Lenguaje de programación principal
- **Android SDK** - Framework de desarrollo
- **View Binding** - Enlace de vistas type-safe
- **Fragment Manager** - Gestión de fragments
- **Material Design** - Componentes de UI
- **ConstraintLayout** - Layout principal
- **CardView** - Presentación de contenido

## 📱 Funcionalidades por Pantalla

### MainActivity + WelcomeFragment
- ✅ Formulario de entrada de nombre
- ✅ Validación de campos
- ✅ Botón "Saludar" con mensaje personalizado
- ✅ Botón "Limpiar" para resetear campos
- ✅ Navegación a SecondFragment (overlay)
- ✅ Navegación a SecondActivity con datos

### SecondFragment
- ✅ Contador interactivo (+1/-1)
- ✅ Botón cerrar que regresa al fragment anterior
- ✅ Overlay sobre el fragment principal
- ✅ Manejo del backstack

### SecondActivity
- ✅ Recepción y visualización de datos del intent
- ✅ Botón regresar a MainActivity
- ✅ Botón cerrar activity
- ✅ ActionBar con navegación

## 🎯 Conceptos Aprendidos

### 🧩 Fragments
- **Ciclo de vida**: `onCreateView()`, `onViewCreated()`
- **Fragment Transactions**: `add()`, `replace()`, `commit()`
- **BackStack**: `addToBackStack()`, `popBackStack()`
- **Comunicación**: Acceso al Activity padre

### 🏠 Activities
- **Intent y Extras**: Paso de datos entre activities
- **Ciclo de vida**: `onCreate()`, `onSupportNavigateUp()`
- **ActionBar**: Configuración de navegación
- **Transiciones**: Animaciones personalizadas

### 🔗 View Binding
- **Type Safety**: Acceso seguro a vistas
- **Performance**: Sin findViewById()
- **Null Safety**: Prevención de NullPointerException

## 🚀 Cómo Ejecutar

1. **Clonar el repositorio**
   ```bash
   git clone [URL_DEL_REPOSITORIO]
   ```

2. **Abrir en Android Studio**
   - File → Open → Seleccionar carpeta del proyecto

3. **Configurar dispositivo**
   - Conectar dispositivo físico o iniciar emulador
   - API Level mínimo: 21 (Android 5.0)

4. **Ejecutar**
   - Click en el botón "Run" (▶️)
   - O usar `Shift + F10`

## 🎨 Capturas de Pantalla

> Proximamente se añadiran capturas de pantalla de:
> - MainActivity con WelcomeFragment
> - SecondFragment superpuesto
> - SecondActivity con datos recibidos

## 📚 Recursos de Aprendizaje

### 📖 Documentación Oficial
- [Android Fragments Guide](https://developer.android.com/guide/fragments)
- [Activity and Task Design](https://developer.android.com/guide/components/activities)
- [Navigation Component](https://developer.android.com/guide/navigation)

### 🎓 Temas Relacionados
- Fragment Communication
- ViewModel y LiveData
- Navigation Component
- Single Activity Architecture

## 🔄 Próximas Mejoras

- [ ] Implementar Navigation Component
- [ ] Añadir ViewModel para manejo de estado
- [ ] Implementar Safe Args para paso de datos
- [ ] Añadir más tipos de transiciones
- [ ] Implementar Deep Links
- [ ] Añadir tests unitarios

## 👨‍💻 Autor

**Gabriel Huav** - [@gabrielhuav](https://github.com/gabrielhuav)

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para detalles.

---

⭐ **¿Te gustó este proyecto?** ¡Dale una estrella al repositorio!

📧 **¿Tienes preguntas?** Abre un [issue](../../issues) y te ayudaremos.