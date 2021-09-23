<ice:panelPopup id="popupImprConstanciaFechas" draggable="true" styleClass="formulario"
	visible="#{consultaCabezaPlanchaVista.visibleImprConstanciaFechas}" modal="true"
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
				<ice:panelGroup>
					<ice:outputText styleClass="textoLabel"  value="#{etiquetas.txtImprConstanciaFechas} #{consultaCabezaPlanchaVista.fechasConstancia} " />
				</ice:panelGroup>
				<br/>
				<ice:panelGroup>
					<ice:commandButton value="#{etiquetas.lblBtnAceptar}" action="#{consultaCabezaPlanchaVista.actionCloseConstanciaFechas}"/>					
				</ice:panelGroup>
				
			</div>

		</ice:panelGrid>
	</f:facet>

</ice:panelPopup>