<ice:panelPopup id="popupImprResolucion" draggable="true" styleClass="formulario"
	visible="#{consultaCabezaPlanchaVista.visibleImprResolucion}" modal="true"
	style="z-index: 50000; top: 30%; left: 35%; position: absolute; width: 280px;">

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
										<ice:outputLabel value="#{consultaCabezaPlanchaVista.titleImprResolucion}" />
									</h3>
									<table border="0" >
										
										<tr align="left">
											<td class="formularioTabla">
												<ice:outputText id="txtNombreLblRes" value="Nombre:" />
											</td>
											<td class="formularioTabla">
												<ice:outputText id="txtNombreRes"
													value="#{consultaCabezaPlanchaVista.nombreAsociado}" />
											</td>
										</tr>
										
										<tr align="left">
											<td class="formularioTabla">
												<ice:outputText value="#{etiquetas.lblResolucionNro}" />
											</td>
											<td>
												<ice:inputText maxlength="80"
													value="#{consultaCabezaPlanchaVista.resolucionNro}"
													style="width: 358px" onkeypress="return keyCheck(event, this);">
												</ice:inputText>
											</td>
										</tr>
										
										<tr align="left">
											<td class="formularioTabla">
												<ice:outputText value="#{etiquetas.lblResolucionImpugnada}" />
											</td>
											<td>
												<ice:inputText maxlength="20"
													value="#{consultaCabezaPlanchaVista.resolucionImpugnada}"
													style="width: 358px">
												</ice:inputText>
											</td>
										</tr>
										
										<tr align="center">
											<td colspan="2">
												<ice:outputText value="#{etiquetas.lblFormatoArgumentos}" />
											</td>
										</tr>
										
										<tr align="left">
											<td class="formularioTabla">
												<ice:outputText value="#{etiquetas.lblFormatoArgResolucion1}" />
											</td>
											<td>
												<ice:inputText maxlength="80"
													value="#{consultaCabezaPlanchaVista.argumentoResolucion1}"
													style="width: 358px">
												</ice:inputText>
											</td>
										</tr>
										<tr align="left">
											<td class="formularioTabla">
												<ice:outputText value="#{etiquetas.lblFormatoArgResolucion2}" />
											</td>
											<td>
												<ice:inputText maxlength="80"
													value="#{consultaCabezaPlanchaVista.argumentoResolucion2}"
													style="width: 358px">
												</ice:inputText>
											</td>
										</tr>
										<tr align="left">
											<td class="formularioTabla">
												<ice:outputText value="#{etiquetas.lblFormatoArgResolucion3}" />
											</td>
											<td>
												<ice:inputText maxlength="80"
													value="#{consultaCabezaPlanchaVista.argumentoResolucion3}"
													style="width: 358px">
												</ice:inputText>
											</td>
										</tr>
										<tr align="center">
											<td colspan="2">
												<ice:outputText value="#{etiquetas.lblFormatoRazones}" />
											</td>
										</tr>
										<tr align="left">
											<td class="formularioTabla">
												<ice:outputText value="#{etiquetas.lblFormatoRazonResolucion1}" />
											</td>
											<td>
												<ice:inputText maxlength="80"
													value="#{consultaCabezaPlanchaVista.razonResolucion1}"
													style="width: 358px">
												</ice:inputText>
											</td>
										</tr>
										<tr align="left">
											<td class="formularioTabla">
												<ice:outputText value="#{etiquetas.lblFormatoRazonResolucion2}" />
											</td>
											<td>
												<ice:inputText maxlength="80"
													value="#{consultaCabezaPlanchaVista.razonResolucion2}"
													style="width: 358px">
												</ice:inputText>
											</td>
										</tr>
										<tr>
											<td>


											</td>
											<td>
												<ice:commandButton id="btnLoginRes" styleClass="button"
													value="#{etiquetas.btnGenerarReporteDelegadosZona}"
													action="#{consultaCabezaPlanchaVista.generarReporteResolucion}">
												</ice:commandButton>
												<ice:commandButton id="btnLimpiarRes" styleClass="button"
													value="Limpiar"
													action="#{consultaCabezaPlanchaVista.limpiar_formulario_resolucion}">
												</ice:commandButton>
												<ice:commandButton id="btnCancelRes" styleClass="button"
													value="Cancelar"
													action="#{consultaCabezaPlanchaVista.action_cancelar_resolucion}">
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