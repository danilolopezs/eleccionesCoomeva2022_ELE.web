<ice:panelPopup id="detalle" draggable="true" styleClass="formulario"
	visible="#{numerarPlanchasCCEE.visibleNumerarPlancha}" modal="true"
	style="z-index: 50000; top: 30%; left: 35%; position: absolute; width: 280px;">

	<f:facet name="header">
		<ice:panelGrid width="100%" cellpadding="0" cellspacing="0"
			style="text-align: center;" headerClass="icePanelPopupHeader">
			<ice:outputText style="color: #FFFFFF;" value="#{numerarPlanchasCCEE.msgTituloPopUp}" />
		</ice:panelGrid>
	</f:facet>

	<f:facet name="body">
		<ice:panelGrid width="100%" cellpadding="0" cellspacing="0"
			styleClass="font: 11px / 17px tahoma;">
			<div align="center">
				<table>
					<ice:dataTable scrollable="10" id="tablaPlanchasHabiles"
						var="tablaPlancha"
						value="#{numerarPlanchasCCEE.listNumerarPlanchas}">
						<f:facet name="header">
							<div align="center">
								<ice:outputText value="#{etiquetas.lblPlanchas}"
									styleClass="tituloTabla" style="width: 100%" />
							</div>
						</f:facet>
						<ice:column>
							<f:facet name="header">
								<div align="center">
									<ice:outputText value="#{etiquetas.lblDocumento}"
										styleClass="tituloTabla" />
								</div>
							</f:facet>
							<div align="center">
								<ice:outputText value="#{tablaPlancha.noCabezaPlancha}"
									styleClass="textoLabel" />
							</div>
						</ice:column>

						<ice:column>
							<f:facet name="header">
								<div align="center">
									<ice:outputText value="#{etiquetas.lblApellidos}"
										styleClass="tituloTabla" />
								</div>
							</f:facet>

							<div align="center">
								<ice:outputText value="#{tablaPlancha.apellidos}"
									styleClass="textoLabel" />
							</div>
						</ice:column>

						<ice:column>
							<f:facet name="header">
								<div align="center">
									<ice:outputText value="#{etiquetas.lblNombres}"
										styleClass="tituloTabla" />
								</div>
							</f:facet>

							<div align="center">
								<ice:outputText value="#{tablaPlancha.nombres}"
									styleClass="textoLabel" />
							</div>
						</ice:column>

						<ice:column>
							<f:facet name="header">
								<div align="center">
									<ice:outputText value="#{etiquetas.lblNoPrincipales}"
										styleClass="tituloTabla" />
								</div>
							</f:facet>

							<div align="center">
								<ice:outputText value="#{tablaPlancha.noPrincipales}"
									styleClass="textoLabel" />
							</div>
						</ice:column>

						<ice:column>
							<f:facet name="header">
								<div align="center">
									<ice:outputText value="#{etiquetas.lblNoSuplentes}"
										styleClass="tituloTabla" />
								</div>
							</f:facet>

							<div align="center">
								<ice:outputText value="#{tablaPlancha.noSuplentes}"
									styleClass="textoLabel" />
							</div>
						</ice:column>

						<ice:column>
							<f:facet name="header">
								<div align="center">
									<ice:outputText value="#{etiquetas.lblFechaIngr}"
										styleClass="tituloTabla" />
								</div>
							</f:facet>

							<div align="center">
								<ice:outputText value="#{tablaPlancha.fechaInscripcion}"
									styleClass="textoLabel" />
							</div>
						</ice:column>

						<ice:column>
							<f:facet name="header">
								<div align="center">
									<ice:outputText value="#{etiquetas.lblNoSorteo}"
										styleClass="tituloTabla" />
								</div>
							</f:facet>

								<ice:inputText value="#{tablaPlancha.numerarPlancha}"
									styleClass="textoInput" partialSubmit="false"  disabled="#{!tablaPlancha.actualiza}"/>
						</ice:column>
						
						<ice:column>
							<f:facet name="header">
								<div align="center">
									<ice:outputText value="#{etiquetas.lblActualizar}"
										styleClass="tituloTabla" />
								</div>
							</f:facet>

							<div align="center">
								<ice:selectBooleanCheckbox value="#{tablaPlancha.actualiza}"/>
							</div>
						</ice:column>
					</ice:dataTable>
				</table>



				<br />
				<table border="0" align="center" cellpadding="0" cellspacing="0"
					background="">
					<tr>
						<td>
							<ice:commandButton value="#{etiquetas.lblGuardar}"
								action="#{numerarPlanchasCCEE.action_guardarNumeracionPlanchas}" />
						</td>
						<td>
							<ice:commandButton value="#{etiquetas.lblCerrar}"
								action="#{numerarPlanchasCCEE.action_closeCancelar}" />
						</td>

					</tr>
				</table>
			</div>
		</ice:panelGrid>
	</f:facet>
</ice:panelPopup>