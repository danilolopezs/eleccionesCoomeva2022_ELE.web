<ice:panelPopup id="mensajeInit" draggable="true" styleClass="formulario"
	visible="#{mensajesVista.visible}" modal="true"
	style="z-index: 50000; top: 30%; left: 35%; position: absolute; width:620px;">

	<f:facet name="header">
		<ice:panelGrid width="100%" cellpadding="0" cellpacing="0"
			style="text-align: center;width:600px;" headerClass="icePanelPopupHeader">
			<ice:outputText style="color: #FFFFFF;" value="Mensaje" />
		</ice:panelGrid>
	</f:facet>

	<f:facet name="body">
		<ice:panelGrid width="600px" cellpadding="0" cellspacing="0"
			styleClass="font: 11px / 17px tahoma;">
			<center>
				<ice:outputText value="#{mensajesVista.mensaje}" styleClass="textoLabel"/>
			</center>
			<br />
			<br />
			<table border="0" align="center" cellpadding="0" cellspacing="0" background="">
				<tr>
					<th width="600px" align="center" style="color: #D4D4D4">
						<ice:commandButton image="./imagenes/boton_salir.gif"
							action="#{mensajesVista.ocultarMensaje}" />
					</th>
				</tr>
				<tr>
				</tr>
			</table>

		</ice:panelGrid>
	</f:facet>

</ice:panelPopup>