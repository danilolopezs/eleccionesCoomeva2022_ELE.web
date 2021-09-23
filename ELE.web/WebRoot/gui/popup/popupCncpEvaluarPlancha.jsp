<ice:panelPopup id="popupVerConceptoInhab" draggable="true"
	visible="#{evaluarPlanchaCCEE.visiblePopUpRecibir}" modal="true"
	style="z-index: 50000; top: 30%; left: 35%; position: absolute; width: 280px;">

	<f:facet name="header">
		<ice:panelGrid width="100%" cellpadding="0" cellspacing="0"
			style="text-align: center;" headerClass="icePanelPopupHeader">
			<ice:outputText style="color: #FFFFFF;" value="Ingresar Concepto" />
		</ice:panelGrid>
	</f:facet>

	<f:facet name="body">
		<ice:panelGrid width="100%" cellpadding="0" cellspacing="0"
			styleClass="font: 11px / 17px tahoma;">
			<center>
				<table>
					<tr>
						<td class="tituloTabla">
							<ice:outputText value="#{etiquetas.lblConceptoCambio}" />
						</td>

					</tr>
					<tr>
						<td>
							<ice:inputTextarea value="#{evaluarPlanchaCCEE.conceptoCambio}"
								styleClass="textoInput" maxlength="200"
								style="width: 236px; height: 88px" />
						</td>
					</tr>
					<tr>
						<td>
							<ice:panelGrid columns="2">
								<ice:outputText value="#{etiquetas.ttlCambioEstado}"
									styleClass="textoLabel" />
								<ice:selectOneMenu id="tipoTransaccion"
									binding="#{evaluarPlanchaCCEE.tiposTransaccion}">
									<f:selectItems
										value="#{evaluarPlanchaCCEE.listTipoTransaccion}" />
								</ice:selectOneMenu>
							</ice:panelGrid>
						</td>
					</tr>
				</table>

			</center>

			<table border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<ice:commandButton value="#{etiquetas.lblGuardar}"
							action="#{evaluarPlanchaCCEE.action_guardarCambios}" />
					</td>
					<td>
						<ice:commandButton
							action="#{evaluarPlanchaCCEE.action_closeConcepto}"
							value="#{etiquetas.lblCancelar}" />
					</td>
				</tr>
			</table>

		</ice:panelGrid>
	</f:facet>

</ice:panelPopup>