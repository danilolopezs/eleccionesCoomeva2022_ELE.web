<ice:panelPopup id="consultProfesiones" modal="true" draggable="false"
	visible="#{consultaHabilidadAsociadosVista.popupVisible}" autoCentre="true"
	styleClass="formulario"
	style="z-index: 50000; top: 30%; left: 35%; position: absolute; width: 280px;">
	<f:facet name="header">
			<ice:panelGrid width="100%" cellpadding="0" cellspacing="0"
				style="text-align: center;" headerClass="icePanelPopupHeader">
				<ice:outputText style="color: #FFFFFF;" value="Consulta Porfesiones" />
			</ice:panelGrid>
	</f:facet>
	
	<f:facet name="body">
		<ice:panelGrid width="100%" cellpadding="0" cellspacing="0"
			styleClass="font: 11px / 17px tahoma;">
			<br />
			<br />
			<table border="0" align="center" cellpadding="0"
				cellspacing="0" background="">
				<tr>
					<ice:panelGroup scrollWidth="600px">										
										
						<ice:dataTable id="tablaProfesiones"
							value="#{consultaHabilidadAsociadosVista.listProfesiones}"
							var="prof"																			
							rows="10">

							<ice:column >
								<f:facet name="header">
									<ice:outputText	value="#{etiquetas.lblCodigoCol}" />
								</f:facet>
								<div align="center">
									<ice:outputText value="#{prof.codigoFiltro}" />	
								</div>
							</ice:column>
							
							<ice:column >
								<f:facet name="header">
									<ice:outputText	value="#{etiquetas.lblNombre}" />
								</f:facet>
								<div align="center">
									<ice:outputText value="#{prof.descripcionFiltro}" />	
								</div>
							</ice:column>
							
							<ice:column >
								<f:facet name="header">
									<ice:outputText	value="#{etiquetas.lblLinkSeleccionar}" />
								</f:facet>
								<div align="center">
									<ice:commandLink actionListener="#{consultaHabilidadAsociadosVista.listener_seleccionarProfesion}"
										 partialSubmit="true"
										 immediate="true"
										 style="color:#0000FF">
											<f:attribute name="codProf" value="#{prof.codigoFiltro}" />
											<f:attribute name="nomProf" value="#{prof.descripcionFiltro}" />
											<ice:outputText value="#{etiquetas.lblLinkSeleccionar}" style="color:#0000FF"/>
									</ice:commandLink>	
								</div>
							</ice:column>														
						
						</ice:dataTable>
						<ice:dataPaginator id="dataScroll"
							for="tablaProfesiones" fastStep="3" paginator="true"
							rowsCountVar="rowsCount" renderFacetsIfSinglePage="false"
							displayedRowsCountVar="displayedRowsCountVar"
							firstRowIndexVar="firstRowIndex"
							lastRowIndexVar="lastRowIndex" pageCountVar="pageCount"
							pageIndexVar="pageIndex" paginatorMaxPages="5">
							<f:facet name="first">
								<ice:graphicImage id="firstpage_1"
									url="/gui/estilos/xmlhttp/css/rime/css-images/arrow-first.gif"
									style="border:none;" />
							</f:facet>
							<f:facet name="last">
								<ice:graphicImage id="lastpage_1"
									url="/gui/estilos/xmlhttp/css/rime/css-images/arrow-last.gif"
									style="border:none;" />
							</f:facet>
							<f:facet name="previous">
								<ice:graphicImage id="previouspage_1"
									url="/gui/estilos/xmlhttp/css/rime/css-images/arrow-previous.gif"
									style="border:none;" />
							</f:facet>
							<f:facet name="next">
								<ice:graphicImage id="nextpage_1"
									url="/gui/estilos/xmlhttp/css/rime/css-images/arrow-next.gif"
									style="border:none;" />
							</f:facet>
							<f:facet name="fastforward">
								<ice:graphicImage id="fastforward_1"
									url="/gui/estilos/xmlhttp/css/rime/css-images/arrow-ff.gif"
									style="border:none;" />
							</f:facet>
							<f:facet name="fastrewind">
								<ice:graphicImage id="fastrewind_1"
									url="/gui/estilos/xmlhttp/css/rime/css-images/arrow-fr.gif"
									style="border:none;" />
							</f:facet>
							<f:param value="#{rowsCount}" />
							<f:param value="#{displayedRowsCountVar}" />
							<f:param value="#{firstRowIndex}" />
							<f:param value="#{lastRowIndex}" />
							<f:param value="#{pageIndex}" />
							<f:param value="#{pageCount}" />
						</ice:dataPaginator>
						<br />
						<br />
					</ice:panelGroup>
									
				</tr>
				<tr>
					<ice:commandButton value="#{etiquetas.lblBtnCancelar}" 
						action="#{consultaHabilidadAsociadosVista.action_closePopup}"/>
				</tr>
			</table>
		</ice:panelGrid>
	</f:facet>
</ice:panelPopup>