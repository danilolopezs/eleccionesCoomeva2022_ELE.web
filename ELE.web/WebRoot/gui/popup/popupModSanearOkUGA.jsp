<ice:panelPopup id="popupRecibirOk" draggable="true"
	visible="#{verSanearPlanchaUGA.visibleConfirmarOk}" modal="true"
	style="z-index: 50000; top: 30%; left: 35%; position: absolute; width: 280px;">

	<f:facet name="header">
		<ice:panelGrid width="100%" cellpadding="0" cellspacing="0"
			style="text-align: center;" headerClass="icePanelPopupHeader">
			<ice:outputText style="color: #FFFFFF;" value="#{etiquetas.ttlExitoso}" />
		</ice:panelGrid>
	</f:facet>

	<f:facet name="body">
		<ice:panelGrid width="100%" cellpadding="0" cellspacing="0"
			styleClass="font: 11px / 17px tahoma;">
			<center>
			<br/>
				<table>
					<tr>
						<td class="textoLabel">
							<ice:outputText value="#{verSanearPlanchaUGA.msgOk}" />
						</td>
					</tr>
				</table>
			</center>
		<br/>
			<table border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<ice:commandButton image="../imagenes/boton_salir.gif"
							action="#{verSanearPlanchaUGA.action_cerrar}" />
					</td>
				</tr>
			</table>
		</ice:panelGrid>
	</f:facet>
</ice:panelPopup>