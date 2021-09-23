<ice:panelPopup id="detalle" draggable="true" styleClass="formulario"
	visible="#{verSanearPlanchaUGA.visibleDetalle}" modal="true"
	style="z-index: 50000; top: 30%; left: 35%; position: absolute; width: 280px;">

	<f:facet name="header">
		<ice:panelGrid width="100%" cellpadding="0" cellspacing="0"
			style="text-align: center;" headerClass="icePanelPopupHeader">
			<ice:outputText style="color: #FFFFFF;" value="Mensaje" />
		</ice:panelGrid>
	</f:facet>

	<f:facet name="body">
		<ice:panelGrid width="100%" cellpadding="0" cellspacing="0"
			styleClass="font: 11px / 17px tahoma;">
			<div align="center">
				<table border="1" style="">
					<tr>
						<td class="tituloTabla">
							<ice:outputText value="#{etiquetas.lblDocumento}" />
						</td>
						<td>
							<div align="center">
								<ice:outputText
									value="#{verSanearPlanchaUGA.nroIdentificacionDetalle}"
									styleClass="textoLabel" />
							</div>
						</td>

					</tr>
					<tr>
						<td class="tituloTabla">
							<ice:outputText value="#{etiquetas.lblNombreAsociado}" />
						</td>
						<td>
							<div align="center">
								<ice:outputText
									value="#{verSanearPlanchaUGA.nombreAsociadoDetalle}"
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
								<ice:outputText value="#{verSanearPlanchaUGA.profesionDetalle}"
									styleClass="textoLabel" />
							</div>
						</td>

					</tr>
					<tr >
						<td class="tituloTabla" colspan="2" align="center">
							<ice:outputText value="#{etiquetas.lblInhabilidades}" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								<ice:inputTextarea value="#{verSanearPlanchaUGA.inhabilidadesDetalle}" readOnly="true" style = "width: 300px; height: 70px;"
									styleClass="textoLabel" />
							</div>
						</td>
					</tr>
				</table>
				<br />
				<table border="0" align="center" cellpadding="0" cellspacing="0"
					background="">
					<tr>
						<th width="70px" align="center" style="color: #D4D4D4">
							<ice:commandButton image="../imagenes/boton_salir.gif"
								action="#{verSanearPlanchaUGA.action_closeDetalle}" />
						</th>
					</tr>
					<tr>
					</tr>
				</table>
			</div>
		</ice:panelGrid>
	</f:facet>
</ice:panelPopup>