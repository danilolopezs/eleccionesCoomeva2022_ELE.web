<ice:panelPopup id="mensajesPlanchaCreada" draggable="true"
	visible="#{verModPlanchaVista.visibleModificoPlancha}" modal="true"
	autoCentre="true">


	<f:facet name="header">
		<table border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td width="100%" align="center">
					<ice:outputText style="color: #FFFFFF; width: 100%;"
						value="#{etiquetas.ttlExitoso}" />
				</td>
			</tr>
		</table>
	</f:facet>

	<f:facet name="body">
		<ice:panelGrid cellpadding="0" cellspacing="0"
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
				<ice:outputText value="#{verModPlanchaVista.mensaje2}"
					styleClass="textoLabel" />
			</center>
			<br />
			<br />
			<table border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td align="center" style="color: #D4D4D4">
						<ice:commandButton image="../imagenes/boton_salir.gif"
							action="#{verModPlanchaVista.action_redireccion}" />
					</td>
				</tr>
				<tr>
				</tr>
			</table>
		</ice:panelGrid>
	</f:facet>
</ice:panelPopup>