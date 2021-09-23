<ice:panelPopup id="mensajeImagen" draggable="true" styleClass="formulario"
	visible="#{crearPlanchaVista.visibleRestriccionesImagen}" modal="true"
	style="z-index: 50000; top: 30%; left: 35%; position: absolute; width: 280px;">

	<f:facet name="header">
		<ice:panelGrid width="100%" cellpadding="0" cellspacing="0"
			style="text-align: center;" headerClass="icePanelPopupHeader">
			<ice:outputText style="color: #FFFFFF;" value="Recuerde" />
		</ice:panelGrid>
	</f:facet>

	<f:facet name="body">
		<ice:panelGrid width="100%" cellpadding="0" cellspacing="0"
			styleClass="font: 11px / 17px tahoma;">
			<center>
				<ice:outputText value="#{crearPlanchaVista.msgRestriccionesImagenes}" styleClass="textoLabel"/>
			</center>
			<br />
			<br />
			<table border="0" align="center" cellpadding="0" cellspacing="0" background="">
				<tr>
					<th width="70px" align="center" style="color: #D4D4D4">
						<ice:commandButton image="../imagenes/boton_salir.gif"
							action="#{crearPlanchaVista.action_ocultarMensaje}" />
					</th>
				</tr>
				<tr>
				</tr>
			</table>

		</ice:panelGrid>
	</f:facet>

</ice:panelPopup>