<ice:panelPopup id="confirmarPopup" draggable="true"
	visible="#{verPlanchaVista.visibleConfirmar}" modal="true"
	style="z-index: 50000; top: 30%; left: 35%; position: absolute; width: 280px;">

	<f:facet name="header">
		<ice:panelGrid width="100%" cellpadding="0" cellspacing="0"
			style="text-align: center;" headerClass="icePanelPopupHeader">
			<ice:outputText style="color: #FFFFFF;" value="#{etiquetas.lblConfirmacion}" />
		</ice:panelGrid>
	</f:facet>

	<f:facet name="body">
		<ice:panelGrid width="100%" cellpadding="0" cellspacing="0"
			styleClass="font: 11px / 17px tahoma;">
			<center>
				<table>
					<tr>
						<td>
							<ice:graphicImage value="#{verPlanchaVista.imagenConf}" >
							</ice:graphicImage>
						</td>
						<td>
							<ice:outputText value="#{verPlanchaVista.mensajeConfirmar}"
								styleClass="textoLabel" />
						</td>
					</tr>
				</table>

			</center>
			<br />
							<table border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td>
						<ice:commandButton value="#{etiquetas.lblBtnInscribir}" 
						action="#{verPlanchaVista.action_inscribir}"/>
							
					</td>
					<td>
						<ice:commandButton
							action="#{verPlanchaVista.action_closeConfirmar}"
							value="#{etiquetas.lblCancelar}" />
					</td>
				</tr>
			</table>

		</ice:panelGrid>
	</f:facet>

</ice:panelPopup>