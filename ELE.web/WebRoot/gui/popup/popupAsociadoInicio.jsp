<ice:panelPopup id="mensajesInicioAso" draggable="true"
							styleClass="formulario"
							visible="#{inicioSesionAsociadoVista.visible}" modal="true"
							style="z-index: 50000; top: 30%; left: 35%; position: absolute; width: 280px; height: 100px;">

							<f:facet name="header">
								<ice:panelGrid width="100%" cellpadding="0" cellspacing="0"
									style="text-align: center;" headerClass="icePanelPopupHeader">
									<ice:outputText style="color: #FFFFFF;" value="#{inicioSesionAsociadoVista.bienvenido}" />
								</ice:panelGrid>
							</f:facet>

							<f:facet name="body">
								<ice:panelGrid width="100%" cellpadding="0" cellspacing="0"
									styleClass="font: 11px / 17px tahoma;">
									<center>
										<ice:outputText value="#{inicioSesionAsociadoVista.msgEntrada}"
											styleClass="textoLabel" />
									</center>
									<br />
									<br />
									<table border="0" align="center" cellpadding="0"
										cellspacing="0" background="">
										<tr>
											<th width="70px" align="center" style="color: #D4D4D4">
												<ice:commandButton value="#{inicioSesionAsociadoVista.btnCerrar}"
													action="#{inicioSesionAsociadoVista.action_redireccion}" />
											</th>
										</tr>
										<tr>
										</tr>
									</table>
								</ice:panelGrid>
							</f:facet>
</ice:panelPopup>