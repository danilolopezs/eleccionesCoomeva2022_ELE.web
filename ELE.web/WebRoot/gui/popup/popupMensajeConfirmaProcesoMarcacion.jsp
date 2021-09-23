<ice:panelPopup id="confirmarPopup" draggable="true"
	visible="#{ejecutarProcesoMarcacionAsos.visibleConfirmar}" modal="true"
	style="z-index: 50000; top: 30%; left: 35%; position: absolute; width: 280px;">

	<f:facet name="header">
		<ice:panelGrid width="100%" cellpadding="0" cellspacing="0"
			style="text-align: center;" headerClass="icePanelPopupHeader">
			<ice:outputText style="color: #FFFFFF;" value="Confirmacion" />
		</ice:panelGrid>
	</f:facet>

	<f:facet name="body">
		<ice:panelGrid width="100%" cellpadding="0" cellspacing="0"
			styleClass="font: 11px / 17px tahoma;">
			<center>
				<table>
					<tr>
						<td>
							<ice:graphicImage value="../imagenes/Symbol-Exclamation.png" />
						</td>
						<td>
							<ice:outputText value="#{etiquetas.msgEjecucionProcesoHabilidad}"
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
						<ice:commandButton value="Si" action="#{ejecutarProcesoMarcacionAsos.actionEjecutarProceso}" onclick="disableButton(this);"/> 
					</td>
					<td>
						<ice:commandButton	action="#{ejecutarProcesoMarcacionAsos.actionCloseConfirmar}"
							value="No" />
					</td>
				</tr>
			</table>

		</ice:panelGrid>
	</f:facet>

</ice:panelPopup>