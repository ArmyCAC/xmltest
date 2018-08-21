package com.zte;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class RRCXMLReader {
    public static void main(String[] argv) {
        String filename = "D:\\DocTest\\xmltest\\src\\com\\zte\\PM201807082115+0800_20180708.2045+0800-2100+0800_CELLRRCCONN.xml";
        List<CellRRCConn> cellRRCConnList = parseXML(filename);
        for (CellRRCConn cellRRCConn : cellRRCConnList) {
            System.out.println(cellRRCConn.toString());
        }

    }

    private static List<CellRRCConn> parseXML(String filename) {
        List<CellRRCConn> cellRRCConnList = new ArrayList<>();
        CellRRCConn cellRRCConn = new CellRRCConn();

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

//      使用“遍历”的方式，createXMLEventReader()
//      还有一种是利用“光标”的方式，createXMLStreamReader()
        try {
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(filename));
            while (xmlEventReader.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
//                System.out.println(xmlEvent.getEventType());
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    String qName = startElement.getName().getLocalPart();
                    System.out.println("<" + qName + ">");
                    switch (qName) {
                        case ("fileHeader"):
                            xmlEvent = xmlEventReader.nextEvent();
                            Attribute vendorName = startElement.getAttributeByName(new QName("vendorName"));
                            if (vendorName != null) {
                                cellRRCConn.setVendorName(vendorName.getValue());
                                System.out.println("vendorName 解析完毕.........\n");
                            } else break;
                            break;
                        case ("granPeriod"):
                            xmlEvent = xmlEventReader.nextEvent();
                            Attribute endTime = startElement.getAttributeByName(new QName("endTime"));
                            if (endTime != null) {
                                cellRRCConn.setEndTime(endTime.getValue());
                                System.out.println("endTime 解析完毕.........\n");
                            } else break;
                            break;
                        case ("managedElement"):
                            xmlEvent = xmlEventReader.nextEvent();
                            Attribute localDn = startElement.getAttributeByName(new QName("localDn"));
                            if (localDn != null) {
//                                String[] tmp = localDn.getValue().split(",");
                                cellRRCConn.setEnodebID((localDn.getValue().substring(36,42)));
                                System.out.println("ENODEBID 解析完毕.........\n");
                            } else break;
                            break;
                        case ("measTypes"):
                            xmlEvent = xmlEventReader.nextEvent();
                            Attribute measTypes = startElement.getAttributeByName();
                            if (measTypes != null) {
//                                String[] tmp = localDn.getValue().split(",");
                                cellRRCConn.setEnodebID((localDn.getValue().substring(36,42)));
                                System.out.println("ENODEBID 解析完毕.........\n");
                            } else break;
                            break;
                    }
                } else if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    System.out.println("</" + endElement.getName().getLocalPart() + ">");
                    if (endElement.getName().getLocalPart().equals("measValue")) {
                        cellRRCConnList.add(cellRRCConn);
                        cellRRCConn = new CellRRCConn();
                    }
                }
            }
            } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return cellRRCConnList;
    }}

//                    if (qName.equals("measData")) {
//                        cellRRCConn = new CellRRCConn();
//                    }else if(qName.equals("managedElement")) {
//                        xmlEvent = xmlEventReader.nextEvent();
//                        Attribute localDn = startElement.getAttributeByName(new QName("localDn"));
//                        if(localDn != null) {
//                            cellRRCConn.setEnodebID(Integer.parseInt(localDn.getValue().substring(36,42)));
//                        }
//                    }else if(startElement.getName().getLocalPart().equals("granPeriod")) {
//                        xmlEvent = xmlEventReader.nextEvent();
//                        Attribute endTime = startElement.getAttributeByName(new QName("endTime"));
//                        if(endTime != null) {
//                            cellRRCConn.setEndTime(endTime.getValue());
//                        }
//                    }else if(startElement.getName().getLocalPart().equals("measTypes")) {
//                        xmlEvent = xmlEventReader.nextEvent();
//                        String[] strsID = xmlEvent.asCharacters().getData().split(" ");
//                        cellRRCConn.setMeasTypes(strsID);
//                    }else if(startElement.getName().getLocalPart().equals("measResults")) {
//                        xmlEvent = xmlEventReader.nextEvent();
//                        String[] strsVal = xmlEvent.asCharacters().getData().split(" ");
//                        cellRRCConn.setMeasResults(strsVal);
//                        cellRRCConnList.add(cellRRCConn);
//                    }else if(startElement.getName().getLocalPart().equals("measValue")) {
//                        xmlEvent = xmlEventReader.nextEvent();
//                        Attribute measObjLdn = startElement.getAttributeByName(new QName("measObjLdn"));
//                        if(measObjLdn != null) {
//                            if(Integer.parseInt(measObjLdn.getValue().substring(22,28)) == cellRRCConn.getEnodebID())
//                                cellRRCConn.setCellID(Integer.parseInt(measObjLdn.getValue().substring(36)));
//
//                        }
//                    }
//                }