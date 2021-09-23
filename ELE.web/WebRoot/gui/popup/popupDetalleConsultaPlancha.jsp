<ice:panelPopup id="planchasConsulta" draggable="false"
	styleClass="formulario"
	visible="#{consultaDetallePlanchas.visiblePlancha}" modal="true"
	style="z-index: 50000; top: 30%; left: 35%; position: absolute; width: 280px;">

	<f:facet name="header">
		<ice:panelGrid width="100%" cellpadding="0" cellspacing="0"
			style="text-align: center;" headerClass="icePanelPopupHeader">
			<ice:outputText style="color: #00000;"
				value="Consulta de Planchas Aceptadas por Zona Electoral" />
		</ice:panelGrid>
	</f:facet>

	<f:facet name="body">
		<ice:panelGrid width="100%" cellpadding="0" cellspacing="0"
			styleClass="font: 11px / 17px tahoma;">

			<div align="center">
				<table cellpadding="0" cellspacing="0" width="824">
					<tr>
						<td width="130" align="center" valign="top" bgcolor="#ABD673">

						</td>

						<td align="center" style="background: white; width: 706px;">
							<br />
							<br />
							<table border="2" width="500px">
								<tr>
									<td colspan="2" align="center">
										<table border="1" style="width: 100%">
											<!-- <tr>
												 <td class="tituloTabla">
													<ice:outputText value="#{etiquetas.lblDocumento}" />
												</td>
												<td style="width: 33%">
													<div align="center">
														<ice:outputText
															value="#{consultaDetallePlanchas.nroCabezaPlancha}"
															styleClass="textoLabel" />
													</div>
												</td> 
												
											</tr>-->
											<tr>
												<td class="tituloTabla" style="width: 33%">
													<ice:outputText value="#{etiquetas.lblNombreAsociado}" />
												</td>
												<td>
													<div align="center">
														<ice:outputText
															value="#{consultaDetallePlanchas.nombreCabezaPlancha}"
															styleClass="textoLabel" />
													</div>
												</td>
												<td rowspan="4" style="width: 33%">
													<div align="center">
														<ice:graphicImage
															value="#{consultaDetallePlanchas.imagenCabPlancha}"
															width="108" height="108" />
													</div>
												</td>
											</tr>
											<tr>
												<td class="tituloTabla">
													<ice:outputText value="#{etiquetas.lblAntiguedad}" />
												</td>
												<td>
													<div align="center">
														<ice:outputText
															value="#{consultaDetallePlanchas.antiguedad}"
															styleClass="textoLabel" />
													</div>
												</td>

											</tr>
											<tr>
												<td class="tituloTabla">
													<ice:outputText value="#{etiquetas.lblProfesion}" />
												</td>
												<td>
													<div align="center">
														<ice:outputText
															value="#{consultaDetallePlanchas.profesion}"
															styleClass="textoLabel" />
													</div>
												</td>

											</tr>
											<tr>
												<td class="tituloTabla">
													<ice:outputText value="#{etiquetas.lblZona}" />
												</td>
												<td>
													<div align="center">
														<ice:outputText value="#{consultaDetallePlanchas.zona}"
															styleClass="textoLabel" />
													</div>
												</td>

											</tr>
										</table>
									</td>
								</tr>
								<tr valign="top">
									<td width="50%">
										<table cellpadding="0" cellspacing="0"
											style="width: 100%; height: 100%;">
											<tr>
												<td class="tituloTabla">
													<ice:outputText value="#{etiquetas.ttlExperiencia}" />
												</td>
											</tr>
											<tr>
												<td>
													<ice:dataTable id="tablaEmpresaCargo" var="tablaEC"
														value="#{consultaDetallePlanchas.listExpLaboral}"
														style="width:100%; height:100%;">

														<ice:column>
															<f:facet name="header">
																<div align="center">
																	<ice:outputText value="#{etiquetas.lblEmpresa}" />
																</div>
															</f:facet>
															<div align="center">
																<ice:outputText value="#{tablaEC.empresa}"
																	styleClass="textoLabel" />
															</div>
														</ice:column>
														<ice:column>
															<f:facet name="header">
																<div align="center">
																	<ice:outputText value="#{etiquetas.lblCargo}" />
																</div>
															</f:facet>
															<div align="center">
																<ice:outputText value="#{tablaEC.cargo}"
																	styleClass="textoLabel" />
															</div>
														</ice:column>
													</ice:dataTable>
												</td>
											</tr>
										</table>
									</td>

									<td width="50%">
										<table style="width: 100%;">
											<tr>
												<td class="tituloTabla">
													<ice:outputText value="#{etiquetas.lblCargoDirectivo}" />
												</td>
											</tr>
											<tr>
												<td align="center">
													<ice:outputText
														value="#{consultaDetallePlanchas.cargosDirectivos}"
														styleClass="textoLabel" />
												</td>
											</tr>
										</table>
									</td>
								</tr>

								<tr>
									<td colspan="2">
										<table border="0" style="width: 100%;">
											<tr>
												<td class="tituloTabla">
													<ice:outputText value="#{etiquetas.lblOtrosEstudios}" />
												</td>

											</tr>
											<tr>
												<td>
													<div align="center">
														<ice:outputText style="width:500px; font:11px tahoma"
															value="#{consultaDetallePlanchas.estudios1}" />
													</div>
												</td>
											</tr>
											<tr>
												<td>
													<div align="center">
														<ice:outputText style="width:500px; font:11px tahoma"
															value="#{consultaDetallePlanchas.estudios2}" />
													</div>
												</td>
											</tr>
										</table>

									</td>
								</tr>
								<tr>
									<td colspan="2" valign="top">
										<center>
											<table style="width: 100%;">
												<tr valign="top">
													<td>

														<ice:dataTable id="tablaPrincipales" var="priTabla"
															value="#{consultaDetallePlanchas.listPrincipalesDTO}"
															width="100%">
															<!-- <ice:column>
															<f:facet name="header">
																<div align="center">
																	<ice:outputText value="#{etiquetas.lblCedulaPrincipal}"
																		styleClass="" />
																</div>
															</f:facet>

															<ice:outputText value="#{priTabla.nroPriIdentificacion}"
																styleClass="textoLabel" />

														</ice:column> -->
															<ice:column>
																<f:facet name="header">
																	<div align="center">
																		<ice:outputText
																			value="#{etiquetas.lblNombrePrincipal}" />
																	</div>
																</f:facet>
																<center>
																	<ice:outputText value="#{priTabla.nombreCompleto}"
																		styleClass="textoLabel" />
																</center>
															</ice:column>
															<!--  <ice:column>
															<f:facet name="header">
																<div align="center">
																	<ice:outputText value="#{etiquetas.lblEstado}" />
																</div>
															</f:facet>
															<center>
																<ice:graphicImage value="#{priTabla.imagenEstado}" />
															</center>
														</ice:column>-->

														</ice:dataTable>

													</td>
													<td>
														<center>
															<ice:dataTable id="tablaSuplentes" var="suTabla"
																value="#{consultaDetallePlanchas.listSuplentesDTO}"
																width="100%">
																<!-- <ice:column>
															<f:facet name="header">
																<div align="center">
																	<ice:outputText value="#{etiquetas.lblCedulaSuplentes}" />
																</div>
															</f:facet>

															<ice:outputText value="#{suTabla.nroSuIdentificacion}"
																styleClass="textoLabel" />

														</ice:column> -->
																<ice:column style="width: 100%;">
																	<f:facet name="header">
																		<div align="center">
																			<ice:outputText
																				value="#{etiquetas.lblNombreSuplentes}" />
																		</div>
																	</f:facet>
																	<center>
																		<ice:outputText value="#{suTabla.nombreCompleto}"
																			styleClass="textoLabel" />
																	</center>
																</ice:column>

																<!--  <ice:column>
															<f:facet name="header">
																<div align="center">
																	<ice:outputText value="#{etiquetas.lblEstado}" />
																</div>
															</f:facet>
															<center>
																<ice:graphicImage value="#{suTabla.imagenEstado}" />
															</center>
														</ice:column>-->
															</ice:dataTable>
														</center>
													</td>

												</tr>
											</table>
										</center>
									</td>
								</tr>

							</table>
							<br />
							<br />
						</td>
						<td width="130" align="center" valign="top" bgcolor="#ABD673">

						</td>
					</tr>
					<tr>
						<td>
						</td>
						<td>
							<div align="center">
								<table>
									<tr>
										<td>
											<ice:commandButton value="#{etiquetas.lblBack}"
												action="#{consultaDetallePlanchas.action_back}"
												disabled="#{consultaDetallePlanchas.renderButtonBack}"
												image="#{consultaDetallePlanchas.imagenBackButton}" />
										</td>
										<td>
											<ice:outputText
												value="#{consultaDetallePlanchas.cantidadPlanchas}"
												styleClass="textoLabelBold" />
										</td>
										<td>

											<ice:commandButton value="#{etiquetas.lblNext}"
												action="#{consultaDetallePlanchas.action_next}"
												disabled="#{consultaDetallePlanchas.renderButtonNext}"
												image="#{consultaDetallePlanchas.imagenNextButton}" />
										</td>

									</tr>
								</table>
							</div>
						</td>
						<td align="right" colspan="3">
							<ice:commandButton value="#{etiquetas.lblCerrar}"
								action="#{consultaDetallePlanchas.action_cerrar}"
								image="./imagenes/close.png" />
						</td>
					</tr>
				</table>
			</div>

		</ice:panelGrid>

	</f:facet>
</ice:panelPopup>