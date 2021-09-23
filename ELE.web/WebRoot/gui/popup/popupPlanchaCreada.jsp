<ice:panelPopup id="mensajesPlanchaCreada" draggable="true"
	styleClass="formulario"
	visible="#{verPlanchaVista.visiblePlanchaCreada}" modal="true"
	style="z-index: 50000; top: 30%; left: 35%; position: absolute; width: 280px;">

	<f:facet name="header">
		<ice:panelGrid width="100%" cellpadding="0" cellspacing="0"
			style="text-align: center;" headerClass="icePanelPopupHeader">
			<ice:outputText style="color: #FFFFFF;"
				value="#{etiquetas.ttlExitoso}" />
		</ice:panelGrid>
	</f:facet>

	<f:facet name="body">
		<ice:panelGrid width="100%" cellpadding="0" cellspacing="0"
			styleClass="font: 11px / 17px tahoma;">
			<center>
				<ice:outputText value="#{etiquetas.msgExitoso}"
					styleClass="textoLabel" />
			</center>
			<br />
			<center>
				<ice:outputText value="#{etiquetas.lblCerrarPDF}"
					styleClass="textoLabel" />
			</center>
			<br />
			<center>
				<ice:outputText value="#{verPlanchaVista.mensaje2}"
					styleClass="textoLabel" />
			</center>
			<br />
			<br />
			<table border="0" align="center" cellpadding="0" cellspacing="0"
				background="">
				<tr>
					<th width="70px" align="center" style="color: #D4D4D4">
						<ice:commandButton image="../imagenes/boton_salir.gif"
							action="#{verPlanchaVista.action_redireccion}" />
					</th>
				</tr>
				<tr>
				</tr>
			</table>
		</ice:panelGrid>
	</f:facet>
</ice:panelPopup>