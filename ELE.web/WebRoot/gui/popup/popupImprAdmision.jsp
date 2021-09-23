<ice:panelPopup id="popupImprAdmision" draggable="true" styleClass="formulario"
	visible="#{consultaCabezaPlanchaVista.visibleImprAdmision}" modal="true"
	style="z-index: 50000; top: 30%; left: 35%; width: 280px; height: 250px;">

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
					<ice:outputText styleClass="textoLabel"  value="#{etiquetas.txtImprAdmision}" />
					
					<ice:panelGrid column="2">											
						<ice:outputText value="#{etiquetas.lblResolucionNro}"/>				
						<ice:inputText maxlength="80"
							value="#{consultaCabezaPlanchaVista.resolucionNro}">
						</ice:inputText>
						
						<ice:outputText value="#{etiquetas.lblnumActa}"></ice:outputText>
						<ice:inputText maxlength="20"
							value="#{consultaCabezaPlanchaVista.numActa}">
						</ice:inputText>	
					</ice:panelGrid>
				</ice:panelGroup>	
				<br/>
				
				<ice:panelGroup>
					<ice:commandButton value="#{etiquetas.lblImprAdmision}" action="#{consultaCabezaPlanchaVista.actionImprimirAdmisionPdf}"/>					
				</ice:panelGroup>				
			</div>

		</ice:panelGrid>
	</f:facet>

</ice:panelPopup>