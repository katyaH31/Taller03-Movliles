package com.example.testeableapp

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import com.example.testeableapp.ui.Screens.TipCalculatorScreen
import org.junit.Rule
import org.junit.Test

/**
* UI tests for TipCalculatorScreen using Jetpack Compose Testing.
* These tests validate that key components behave correctly when interacted with.
*/
class CalculateTestUi {

    @get:Rule
    val composeTestRule = createComposeRule()

    /**
     * Test that verifies the tip value updates correctly when the "Round up tip" checkbox is selected.
     * Given a 15% tip on $100, the result should be 15.0. Since it's already whole, no ceil effect is applied.
     */
    @Test
    fun redondearPropina_cambiaCalculo() {
        composeTestRule.setContent { TipCalculatorScreen() }

        // Step 1: Enter amount
        composeTestRule
            .onNodeWithText("Monto de la cuenta")
            .performTextInput("100")

        // Step 2: Activate rounding checkbox
        composeTestRule
            .onNodeWithText("Redondear propina")
            .performClick()

        // Step 3: Check that tip shows the expected value
        composeTestRule
            .onNodeWithText("Propina: $15.00") // 100 * 15% = 15.0
            .assertExists()
    }

    /**
     * Test that simulates changing the tip percentage via the slider and validates that the tip value updates accordingly.
     * Starts at 15%, swipes right to increase percentage, then verifies that tip changed (e.g. to 22.00).
     */
    @Test
    fun cambiarSliderPorcentaje_actualizaPropina() {
        composeTestRule.setContent { TipCalculatorScreen() }

        // Paso 1: Ingresar monto
        composeTestRule
            .onNodeWithText("Monto de la cuenta")
            .performTextInput("100")

        // Paso 2: Verificar propina por defecto
        composeTestRule
            .onNodeWithText("Propina: $15.00")
            .assertExists()

        // Paso 3: Mover slider (basado en el texto de porcentaje)
        composeTestRule
            .onNodeWithText("Porcentaje de propina: 15%")
            .performTouchInput { swipeRight() }

        // Paso 4: Verificar nuevo valor aproximado (22.00 según tu captura)
        composeTestRule
            .onNodeWithText("Propina:", substring = true)
            .assertTextContains("22.00")
    }
    /**
     * Test that ensures all key UI elements are visible when the screen loads:
     * - Amount field
     * - Tip percentage text
     * - Number of people section
     */
    @Test
    fun validarElementosVisibles_enPantallaPrincipal() {
        composeTestRule.setContent { TipCalculatorScreen() }

        // Validar campo de monto
        composeTestRule
            .onNodeWithText("Monto de la cuenta")
            .assertIsDisplayed()

        // Validar texto del porcentaje
        composeTestRule
            .onNodeWithText("Porcentaje de propina: 15%")
            .assertIsDisplayed()

        // Validar texto "Número de personas"
        composeTestRule
            .onNodeWithText("Número de personas: 1")
            .assertIsDisplayed()
    }
 // PRUEBAS UI ADICIONALES
    /**
     * Test that verifies increasing the number of people updates the displayed value.
     * It ensures that clicking "+" correctly reflects "Número de personas: 2"
     */
    @Test
    fun alIncrementarNumeroDePersonas_seActualizaTexto() {
        composeTestRule.setContent { TipCalculatorScreen() }

        // Ingresar monto
        composeTestRule
            .onNodeWithText("Monto de la cuenta")
            .performTextInput("100")

        // Verificar que el texto inicial sea para 1 persona
        composeTestRule
            .onNodeWithText("Número de personas: 1")
            .assertExists()

        // Presionar el botón "+"
        composeTestRule
            .onNodeWithText("+")
            .performClick()

        // Verificar que el número cambió a 2 personas
        composeTestRule
            .onNodeWithText("Número de personas: 2")
            .assertExists()
    }

}
