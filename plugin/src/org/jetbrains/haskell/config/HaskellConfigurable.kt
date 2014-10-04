package org.jetbrains.haskell.config

import com.intellij.openapi.options.Configurable
import org.jetbrains.haskell.icons.HaskellIcons
import javax.swing.*
import com.intellij.ui.DocumentAdapter
import javax.swing.event.DocumentEvent
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import org.jetbrains.haskell.util.setConstraints
import java.awt.GridBagConstraints
import org.jetbrains.haskell.util.gridBagConstraints
import java.awt.Insets
import java.awt.GridBagLayout
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory

public class HaskellConfigurable() : Configurable {
    private var isModified = false
    private val cabalPathField = TextFieldWithBrowseButton()
    private val cabalDataPathField = TextFieldWithBrowseButton()
    private val ghcMod = TextFieldWithBrowseButton()
    private val ghcModi = TextFieldWithBrowseButton()
    private val buildWrapper = TextFieldWithBrowseButton()
    private val scan = TextFieldWithBrowseButton()
    private val hLint = TextFieldWithBrowseButton()


    override fun getDisplayName(): String {
        return "Haskell"
    }

    override fun isModified(): Boolean = isModified

    override fun createComponent(): JComponent {

        cabalPathField.addBrowseFolderListener(
                "Select cabal execurtable",
                null,
                null,
                FileChooserDescriptorFactory.createSingleLocalFileDescriptor())

        cabalDataPathField.addBrowseFolderListener(
                "Select data cabal directory",
                null,
                null,
                FileChooserDescriptorFactory.createSingleFolderDescriptor())

        ghcMod.addBrowseFolderListener(
                "Select ghc-mod execurtable",
                null,
                null,
                FileChooserDescriptorFactory.createSingleLocalFileDescriptor())

        buildWrapper.addBrowseFolderListener(
                "Select build-wrapper execurtable",
                null,
                null,
                FileChooserDescriptorFactory.createSingleLocalFileDescriptor())

        scan.addBrowseFolderListener(
                "Select scan execurtable",
                null,
                null,
                FileChooserDescriptorFactory.createSingleLocalFileDescriptor())

        hLint.addBrowseFolderListener(
                "Select hLint execurtable",
                null,
                null,
                FileChooserDescriptorFactory.createSingleLocalFileDescriptor())


        val result = JPanel(GridBagLayout())

        val listener: DocumentAdapter = object : DocumentAdapter() {

            override fun textChanged(e: DocumentEvent?) {
                isModified = true;
            }

        };

        cabalPathField.getTextField()!!.getDocument()!!.addDocumentListener(listener)
        cabalDataPathField.getTextField()!!.getDocument()!!.addDocumentListener(listener)
        ghcMod.getTextField()!!.getDocument()!!.addDocumentListener(listener)
        buildWrapper.getTextField()!!.getDocument()!!.addDocumentListener(listener)
        scan.getTextField()!!.getDocument()!!.addDocumentListener(listener)
        hLint.getTextField()!!.getDocument()!!.addDocumentListener(listener)

        val base = gridBagConstraints {
            insets = Insets(2, 0, 2, 3)
        }


        fun addLabeledControl(row: Int, label: String, component: JComponent) {
            result.add(JLabel(label), base.setConstraints {
                anchor = GridBagConstraints.LINE_START
                gridx = 0;
                gridy = row;
            })

            result.add(component, base.setConstraints {
                gridx = 1;
                gridy = row;
                fill = GridBagConstraints.HORIZONTAL
                weightx = 1.0
            })

            result.add(Box.createHorizontalStrut(1), base.setConstraints {
                gridx = 2;
                gridy = row;
                weightx = 0.1
            })
        }

        addLabeledControl(0, "cabal executable", cabalPathField)
        addLabeledControl(1, "cabal data path", cabalDataPathField)
        addLabeledControl(2, "ghc-mod executable", ghcMod)
        addLabeledControl(3, "ghc-modi executable", ghcModi)
        addLabeledControl(4, "buildwrapper executable", buildWrapper)
        addLabeledControl(5, "scan executable", scan)
        addLabeledControl(6, "HLint executable", hLint)


        result.add(JPanel(), gridBagConstraints {
            gridx = 0
            gridy = 5
            weighty = 10.0

        })

        return result
    }

    public fun getIcon(): Icon {
        return HaskellIcons.HASKELL
    }

    override fun apply() {
        val state = HaskellSettings.getInstance().getState()
        state.cabalPath = cabalPathField.getTextField()!!.getText()
        state.cabalDataPath = cabalDataPathField.getTextField()!!.getText()
        state.ghcModPath = ghcMod.getTextField()!!.getText()
        state.ghcModiPath = ghcModi.getTextField()!!.getText()
        state.buildWrapperPath = buildWrapper.getTextField()!!.getText()
        state.scanPath = scan.getTextField()!!.getText()
        state.hLintPath = hLint.getTextField()!!.getText()

        isModified = false
    }

    override fun disposeUIResources() {
    }

    override fun getHelpTopic(): String? = null

    override fun reset() {
        val state = HaskellSettings.getInstance().getState()
        cabalPathField.getTextField()!!.setText(state.cabalPath ?: "")
        cabalDataPathField.getTextField()!!.setText(state.cabalDataPath ?: "")
        ghcMod.getTextField()!!.setText(state.ghcModPath ?: "")
        ghcModi.getTextField()!!.setText(state.ghcModiPath ?: "")
        buildWrapper.getTextField()!!.setText(state.buildWrapperPath ?: "")
        scan.getTextField()!!.setText(state.scanPath ?: "")
        hLint.getTextField()!!.setText(state.hLintPath ?: "")

        isModified = false
    }


}
