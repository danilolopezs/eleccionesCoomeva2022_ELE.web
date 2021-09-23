<ice:panelPopup id="popupImprInadmision" draggable="true" styleClass="formulario"
	visible="#{consultaCabezaPlanchaVista.visibleImprInadmision}" modal="true"
	style="z-index: 50000; top: 30%; left: 35%; position: absolute; width: 500px; height: 350px; color: #FFFFFF;">

	<f:facet name="header">
		<ice:panelGrid width="100%" cellpadding="0" cellspacing="0"
			style="text-align: center;" headerClass="icePanelPopupHeader">
			<ice:outputText style="color: #FFFFFF;" value="" />
		</ice:panelGrid>
	</f:facet>

	<f:facet name="body">
		<ice:panelGrid width="100%" cellpadding="0" cellspacing="0">
			
					<div align="center">
						<table cellpadding="0" cellspacing="0">
							
							<tr>
								

								<td align="center" style="background: white;">
									<h3>
										<ice:outputLabel value="#{etiquetas.btnGenerarReporteInhabilidad}" />
									</h3>
									<table border="0" >
										
										<tr align="left">
											<td class="formularioTabla">
												<ice:outputText id="txtNombreLbl" value="Nombre:" />
											</td>
											<td class="formularioTabla">
												<ice:outputText id="txtNombre"
													value="#{consultaCabezaPlanchaVista.nombreAsociado}" />
											</td>
										</tr>
										<tr>
											<td>
												<h3>
													<ice:outputLabel value="#{etiquetas.lblFormatoRazones}" />
												</h3>
											</td>
										</tr>
										
										<tr align="left">
											<td>
											</td>
											<td>
												<ice:inputText maxlength="80"
													value="#{consultaCabezaPlanchaVista.razon1}"
													style="width: 358px;">
												</ice:inputText>
											</td>
										</tr>
										 
										<tr align="left">
											<td>
											</td>
											<td>
												<ice:inputText maxlength="80"
													value="#{consultaCabezaPlanchaVista.razon2}"
													style="width: 358px">
												</ice:inputText>
											</td>
										</tr>
										<tr align="left">
											<td>
												<ice:outputText value="" />
											</td>
											<td>
												<ice:inputText maxlength="80"
													value="#{consultaCabezaPlanchaVista.razon3}"
													style="width: 358px">
												</ice:inputText>
											</td>
										</tr>
										<tr align="left">
											<td>
												<ice:outputText value="" />
											</td>
											<td>
												<ice:inputText maxlength="80"
													value="#{consultaCabezaPlanchaVista.razon4}"
													style="width: 358px">
												</ice:inputText>
											</td>
										</tr>
										 
										<tr>
											<td>


											</td>
											<td>
												<ice:commandButton id="btnLogin" styleClass="button"
													value="#{etiquetas.btnGenerarReporteDelegadosZona}"
													action="#{consultaCabezaPlanchaVista.generarReporteInadmision}">
												</ice:commandButton>
												<ice:commandButton id="btnLimpiar" styleClass="button"
													value="Limpiar"
													action="#{consultaCabezaPlanchaVista.limpiar_formulario}">
												</ice:commandButton>
												<ice:commandButton id="btnCancel" styleClass="button"
													value="Cancelar"
													action="#{consultaCabezaPlanchaVista.action_cancelar_inadmision}">
												</ice:commandButton>
											</td>
										</tr>

									</table>
									<br />
								</td>
							</tr>

						</table>

						
					</div>

		</ice:panelGrid>
	</f:facet>

</ice:panelPopup>