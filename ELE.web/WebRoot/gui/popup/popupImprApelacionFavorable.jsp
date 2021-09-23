<ice:panelPopup id="popupImprApelacionFavorable" draggable="false"
	visible="#{consultaCabezaPlanchaVista.visibleImprApelacionFavorable}"
	modal="true"
	style="margin-top: 0%; margin-left:5%; position: fixed; width: 480px;">

	<f:facet name="header">
		<ice:panelGrid>
			<ice:outputText style="color: #FFFFFF;" value="" />
		</ice:panelGrid>
	</f:facet>
	<f:facet name="body">
		<ice:panelGrid  cellpadding="0" cellspacing="0">
			<div align="center">
				<table cellpadding="0" cellspacing="1" style="background: white;">
					<thead>
						<tr>
							<th colspan="2">
								<h3>
									<ice:outputLabel
										value="#{etiquetas.lblTituloApelacionFavorable}" />
								</h3>
							</th>
						</tr>
					</thead>
					<tr align="left">
						<td class="formularioTabla">
							<ice:outputText id="txtNombreLblApeFav" value="Nombre:" />
						</td>
						<td class="formularioTabla">
							<ice:outputText id="txtNombreApeFav"
								value="#{consultaCabezaPlanchaVista.nombreAsociado}" />
						</td>
					</tr>
					<tr align="left">
						<td class="formularioTabla">
							<ice:outputText value="#{etiquetas.lblResolucionNro}" />
						</td>
						<td>
							<ice:inputText maxlength="65"
								value="#{consultaCabezaPlanchaVista.resolucionNroApelacion}"
								style="width: 358px" onkeypress="return keyCheck(event, this);">
							</ice:inputText>
						</td>
					</tr>

					<tr align="left">
						<td class="formularioTabla">
							<ice:outputText value="#{etiquetas.lblResolucionImpugnada}" />
						</td>
						<td>
							<ice:inputText maxlength="65"
								value="#{consultaCabezaPlanchaVista.resolucionImpugnadaApelacion}"
								style="width: 358px">
							</ice:inputText>
						</td>
					</tr>
					<tr align="left">
						<td class="formularioTabla">
							<ice:outputText value="#{etiquetas.lblResolucionInterpuesta}" />
						</td>
						<td>
							<ice:inputText maxlength="65"
								value="#{consultaCabezaPlanchaVista.resolucionInterpuesta}"
								style="width: 358px">
							</ice:inputText>
						</td>
					</tr>

					<tr align="center">
						<td colspan="2">
							<ice:outputText
								value="#{etiquetas.lblFormatoArgumentosRecurrente}" />
						</td>
					</tr>

					<tr align="left">
						<td class="formularioTabla">
							<ice:outputText value="#{etiquetas.lblFormatoArgResolucion1}" />
						</td>
						<td>
							<ice:inputText maxlength="65"
								value="#{consultaCabezaPlanchaVista.argumentoRecurrente1}"
								style="width: 358px">
							</ice:inputText>
						</td>
					</tr>
					<tr align="left">
						<td class="formularioTabla">
							<ice:outputText value="#{etiquetas.lblFormatoArgResolucion2}" />
						</td>
						<td>
							<ice:inputText maxlength="65"
								value="#{consultaCabezaPlanchaVista.argumentoRecurrente2}"
								style="width: 358px">
							</ice:inputText>
						</td>
					</tr>

					<tr align="center">
						<td colspan="2">
							<ice:outputText value="#{etiquetas.lblFormatoArgumentosComision}" />
						</td>
					</tr>

					<tr align="left">
						<td class="formularioTabla">
							<ice:outputText value="#{etiquetas.lblFormatoArgResolucion1}" />
						</td>
						<td>
							<ice:inputText maxlength="65"
								value="#{consultaCabezaPlanchaVista.argumentoComision1}"
								style="width: 358px">
							</ice:inputText>
						</td>
					</tr>
					<tr align="left">
						<td class="formularioTabla">
							<ice:outputText value="#{etiquetas.lblFormatoArgResolucion2}" />
						</td>
						<td>
							<ice:inputText maxlength="65"
								value="#{consultaCabezaPlanchaVista.argumentoComision2}"
								style="width: 358px">
							</ice:inputText>
						</td>
					</tr>
					<tr align="center">
						<td colspan="2">
							<ice:outputText
								value="#{etiquetas.lblFormatoPronunciamientoTribunal}" />
						</td>
					</tr>
					<tr align="left">
						<td class="formularioTabla">
							<ice:outputText value="#{etiquetas.lblFormatoPronunciamiento1}" />
						</td>
						<td>
							<ice:inputText maxlength="65"
								value="#{consultaCabezaPlanchaVista.pronunciamientoTribunal1}"
								style="width: 358px">
							</ice:inputText>
						</td>
					</tr>
					<tr align="left">
						<td class="formularioTabla">
							<ice:outputText value="#{etiquetas.lblFormatoPronunciamiento2}" />
						</td>
						<td>
							<ice:inputText maxlength="65"
								value="#{consultaCabezaPlanchaVista.pronunciamientoTribunal2}"
								style="width: 358px">
							</ice:inputText>
						</td>
					</tr>
					<tr align="center">
						<td colspan="2">
							<ice:outputText value="#{etiquetas.lblFormatoEnConsecuencia}" />
						</td>
					</tr>
					<tr align="left">
						<td class="formularioTabla">
							<ice:outputText value="#{etiquetas.lblFormatoConsecuencia1}" />
						</td>
						<td>
							<ice:inputText maxlength="65"
								value="#{consultaCabezaPlanchaVista.consecuenciaTribunal1}"
								style="width: 358px">
							</ice:inputText>
						</td>
					</tr>
					<tr align="left">
						<td class="formularioTabla">
							<ice:outputText value="#{etiquetas.lblFormatoConsecuencia2}" />
						</td>
						<td>
							<ice:inputText maxlength="65"
								value="#{consultaCabezaPlanchaVista.consecuenciaTribunal2}"
								style="width: 358px">
							</ice:inputText>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<ice:commandButton id="btnLoginApeFav" styleClass="button"
								value="#{etiquetas.btnGenerarReporteDelegadosZona}"
								action="#{consultaCabezaPlanchaVista.generarReporteResolucion}">
							</ice:commandButton>
							<ice:commandButton id="btnLimpiarApeFav" styleClass="button"
								value="Limpiar"
								action="#{consultaCabezaPlanchaVista.limpiar_formulario_apelacion_favorable}">
							</ice:commandButton>
							<ice:commandButton id="btnCancelApeFav" styleClass="button"
								value="Cancelar"
								action="#{consultaCabezaPlanchaVista.action_cancelar_apelacion_favorable}">
							</ice:commandButton>
						</td>
					</tr>

				</table>
			</div>
		</ice:panelGrid>
	</f:facet>
</ice:panelPopup>