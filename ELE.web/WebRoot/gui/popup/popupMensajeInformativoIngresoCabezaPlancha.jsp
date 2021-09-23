<ice:panelPopup id="mensajeInformativoCabeza" draggable="true" styleClass="formulario"
	visible="#{registrarInfoCabezaPlancha.mensajeInformativo}" modal="true"
	style="z-index: 50000; top: 30%; left: 35%; position: absolute; width: 280px; height: auto;">

	<f:facet name="header">
		<ice:panelGrid width="100%" cellpadding="0" cellspacing="0"
			style="text-align: center;" headerClass="icePanelPopupHeader">
			<ice:outputText style="color: #FFFFFF;" value="Mensaje" />
		</ice:panelGrid>
	</f:facet>

	<f:facet name="body">
		<ice:panelGrid width="100%" cellpadding="0" cellspacing="0"
			styleClass="font: 11px / 17px tahoma;">
			<center>
			    <ice:outputText value="#{registrarInfoCabezaPlancha.mensajeIngresoInfoCabezaPlancha}" styleClass="textoLabel"/>
				<!-- <ice:outputText value="#{etiquetas.lblMensajeInfoCabezaPlancha}" styleClass="textoLabel"/>-->
			</center>
			<br />
			<br />
			<table border="0" align="center" cellpadding="0" cellspacing="0" background="">
				<tr>
					<th width="70px" align="center" style="color: #D4D4D4">
						<ice:commandButton image="../imagenes/boton_salir.gif"
							action="#{registrarInfoCabezaPlancha.action_cerrar_mensaje_informativo}" />
					</th>
				</tr>
				<tr>
				</tr>
			</table>

		</ice:panelGrid>
	</f:facet>

</ice:panelPopup>