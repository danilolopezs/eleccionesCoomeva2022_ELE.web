<ice:panelPopup id="popupFoto" draggable="true" styleClass="formulario"
	visible="#{crearPlanchaVista.visibleFoto}" modal="true"
	style="z-index: 50000; top: 30%; left: 35%; position: absolute; width: 460px;">

	<f:facet name="header">
		<ice:panelGrid width="100%" cellpadding="0" cellspacing="0"
			style="text-align: center;" headerClass="icePanelPopupHeader">
			<ice:outputText style="color: #FFFFFF;" value="Cambiar Foto" />
		</ice:panelGrid>
	</f:facet>

	<f:facet name="body">
		<ice:panelGrid width="100%" cellpadding="0" cellspacing="0"
			styleClass="font: 11px / 17px tahoma;">
			<div align="center">
				<ice:inputFile id="inputFileName" width="200" uploadDirectory="#{etiquetas.pathUpload}" 
					actionListener="#{crearPlanchaVista.uploadFile}" 
					autoUpload = "true"
					title = "#{etiquetas.tooltip}"
					style="width: 381px; height: 50px" />

				<ice:commandButton value="#{etiquetas.lblCancelar}"
					action="#{crearPlanchaVista.action_cancelar}" />
			</div>

		</ice:panelGrid>
	</f:facet>
</ice:panelPopup>