<ice:panelPopup id="popupImprResolucionDelegados" draggable="true" styleClass="formulario"
	visible="#{consultaCabezaPlanchaVista.visibleImprResolucionDelegados}" modal="true"
	style="z-index: 50000; top: 30%; left: 35%; position: absolute; width: 600px; height: 600px; ">

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
								

								<td align="center">
									<h3>
										<ice:outputLabel value="#{consultaCabezaPlanchaVista.titleImprResolucion}" />
									</h3>
									<table border="0" >
										
										<tr align="left">
											<td class="formularioTabla">
												<ice:outputText id="txtNombreLblResDelegado" value="Nombre:" />
											</td>
											<td class="formularioTabla">
												<ice:outputText id="txtNombreResDelegado"
													value="#{consultaCabezaPlanchaVista.nombreAsociado}" />
											</td>
										</tr>
										<tr align="left">
											<td >
												<ice:outputText value="#{etiquetas.lblNombreComision}"
												rendered="#{!consultaCabezaPlanchaVista.visibleDesicionTribunalnDelegados
												 || consultaCabezaPlanchaVista.visibleFavorable}" />
											</td>
											<td>
												<ice:inputText maxlength="20"
													value="#{consultaCabezaPlanchaVista.comisionElectoral}"
													rendered="#{!consultaCabezaPlanchaVista.visibleDesicionTribunalnDelegados
														|| consultaCabezaPlanchaVista.visibleFavorable}"
													style="width: 358px">
												</ice:inputText>
											</td>
										</tr>
										
										<tr align="left">
											<td >
												<ice:outputText value="#{etiquetas.lblNombreAccionante}"
												rendered="#{consultaCabezaPlanchaVista.visibleFavorable}" />
											</td>
											<td>
												<ice:inputText maxlength="20"
													value="#{consultaCabezaPlanchaVista.nombreAccionante}"
													rendered="#{consultaCabezaPlanchaVista.visibleFavorable}"
													style="width: 358px">
												</ice:inputText>
											</td>
										</tr>
										
										<tr align="left">
											<td >
												<ice:outputText value="#{etiquetas.lblResolucionNro}"
												 rendered="#{!consultaCabezaPlanchaVista.visibleFavorable}" />
												 
												 <ice:outputText value="#{etiquetas.lblResolucionModifica}"
												 	rendered="#{consultaCabezaPlanchaVista.visibleFavorable}" />
											</td>
											<td>
												<ice:inputText maxlength="80"
													value="#{consultaCabezaPlanchaVista.resolucionNro}"													
													style="width: 358px" onkeypress="return keyCheck(event, this);">
												</ice:inputText>
											</td>
										</tr>
										
										<tr align="left">
											<td >
												<ice:outputText value="#{etiquetas.lblResolucionImpugnada}"
												rendered="#{!consultaCabezaPlanchaVista.visibleDesicionTribunalnDelegados
													|| consultaCabezaPlanchaVista.visibleFavorable}" />
											</td>
											<td>
												<ice:inputText maxlength="20"
													value="#{consultaCabezaPlanchaVista.resolucionImpugnada}"
													rendered="#{!consultaCabezaPlanchaVista.visibleDesicionTribunalnDelegados
														|| consultaCabezaPlanchaVista.visibleFavorable}"
													style="width: 358px" onkeypress="return keyCheck(event, this);">
												</ice:inputText>
											</td>
										</tr>
										<tr align="left">
											<td >
												<ice:outputText value="#{etiquetas.lblResolucionApelada}"
												rendered="#{consultaCabezaPlanchaVista.visibleDesicionTribunalnDelegados}" />
											</td>
											<td>
												<ice:inputText maxlength="20"
													value="#{consultaCabezaPlanchaVista.resolucionImpugnada}"
													rendered="#{consultaCabezaPlanchaVista.visibleDesicionTribunalnDelegados}"
													style="width: 358px" onkeypress="return keyCheck(event, this);">
												</ice:inputText>
											</td>
										</tr>
										<tr align="left">
											<td >
												<ice:outputText value="#{etiquetas.lblnumActa}"
												rendered="#{consultaCabezaPlanchaVista.visibleDesicionTribunalnDelegados}" />
											</td>
											<td>
												<ice:inputText maxlength="20"
													value="#{consultaCabezaPlanchaVista.numActa}"
													rendered="#{consultaCabezaPlanchaVista.visibleDesicionTribunalnDelegados}"
													style="width: 358px" onkeypress="return keyCheck(event, this);">
												</ice:inputText>
											</td>
										</tr>
										<tr align="left">
											<td >
												<ice:outputText value="#{etiquetas.lblnumActaInterpuesto}"
												rendered="#{consultaCabezaPlanchaVista.visibleDesicionTribunalnDelegados}" />
											</td>
											<td>
												<ice:inputText maxlength="20"
													value="#{consultaCabezaPlanchaVista.numActaTribunal}"
													rendered="#{consultaCabezaPlanchaVista.visibleDesicionTribunalnDelegados}"
													style="width: 358px" onkeypress="return keyCheck(event, this);">
												</ice:inputText>
											</td>
										</tr>
										<tr align="left">
											<td >
												<ice:outputText value="#{etiquetas.lblFechaRecurso}"
												rendered="#{consultaCabezaPlanchaVista.visibleFechaRecurso}" />
											</td>
											<td>
												<ice:selectInputDate value="#{consultaCabezaPlanchaVista.fechaPresentacion}"
													rendered="#{consultaCabezaPlanchaVista.visibleFechaRecurso}"
													renderAsPopup="true">
														<f:convertDateTime type="both"/>
												</ice:selectInputDate>
											</td>
										</tr>
										
										<tr align="center">
											<td colspan="2">
												<ice:outputText value="#{etiquetas.lblFormatoArgumentos}" />
											</td>
										</tr>
										
										<tr align="left">
											<td >
												<ice:outputText value="" />
											</td>
											<td>
												<ice:inputTextarea maxlength="250"
													value="#{consultaCabezaPlanchaVista.argumentoResolucion1}"
													style="width: 358px; height: 100px;">
												</ice:inputTextarea>
											</td>
										</tr>										
										<tr align="center">
											<td colspan="2">
												<ice:outputText value="#{etiquetas.lbldisionTribunal}" rendered="#{consultaCabezaPlanchaVista.visibleDesicionTribunalnDelegados}"/>
											</td>
										</tr>
										<tr align="left">
											<td >
												<ice:outputText value="#{etiquetas.lbldisionTribunal}" 
													rendered="#{consultaCabezaPlanchaVista.visibleDesicionTribunalnDelegados
														|| consultaCabezaPlanchaVista.visibleFavorable}"/>
											</td>
											<td>
												<ice:inputText maxlength="80"
													value="#{consultaCabezaPlanchaVista.desicionTribunal}"
													rendered="#{consultaCabezaPlanchaVista.visibleDesicionTribunalnDelegados
														|| consultaCabezaPlanchaVista.visibleFavorable}"
													style="width: 358px">
												</ice:inputText>
											</td>
										</tr>
										<tr>
											<td>


											</td>
											<td>
												<ice:commandButton id="btnGenerarResDele" styleClass="button"
													value="#{etiquetas.btnGenerarReporteDelegadosZona}"
													action="#{consultaCabezaPlanchaVista.generarReporteResolucion}">
												</ice:commandButton>
												<ice:commandButton id="btnLimpiarResDelegado" styleClass="button"
													value="Limpiar"
													action="#{consultaCabezaPlanchaVista.limpiar_formulario_resolucion}">
												</ice:commandButton>
												<ice:commandButton id="btnCancelResDelegado" styleClass="button"
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