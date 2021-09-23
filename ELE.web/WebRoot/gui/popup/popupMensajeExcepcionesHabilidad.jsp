<ice:panelPopup id="mensajeConfirma" draggable="true"
	styleClass="formulario"
	visible="#{consultaCabezaPlanchaVista.mostrarExcepciones}" modal="true"
	style="z-index: 50000; top: 30%; left: 35%; position: absolute; width: 500px; height: auto;">

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
				<ice:dataTable id="tablaExcepciones" border="1"
					value="#{consultaCabezaPlanchaVista.listaExcepciones}" var="excepcion">

					<ice:column styleClass="textoLabel2">
						<f:facet name="header">
							<ice:outputText value="Regla" />
						</f:facet>
						<div align="left">
						<ice:outputText escape="false" value="#{excepcion.descExcepcion}" />
						</div>
					</ice:column>
				</ice:dataTable>
			</center>
			<br />
			<br />
			<table border="0" align="center" cellpadding="0" cellspacing="0"
				background="">
				<tr>
					<th width="70px" align="center" style="color: #D4D4D4">
						<ice:commandButton image="../imagenes/boton_salir.gif"
							action="#{consultaCabezaPlanchaVista.action_cerrar_mensaje}" />
					</th>
				</tr>
				<tr>
				</tr>
			</table>

		</ice:panelGrid>
	</f:facet>

</ice:panelPopup>