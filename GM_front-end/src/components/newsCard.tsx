import { Card, CardContent } from "@mui/material";
import { ScrollArea, ScrollBar } from "./ui/scroll-area";

import W from "@/assets/w.jpg"

export function NewsCard(){
    return<>
        <Card className="flex-1 rounded-xl shadow-sm">
                <div className="p-5 border-b-2" >
                    <h1 className="text-lg font-bold">Recent News</h1>
                </div>
                <ScrollArea className="whitespace-nowrap rounded-md">
                <div className="flex w-max space-x-4 mt-5">
                    <figure  className="shrink-0 flex mx-5">
                        <div className="overflow-hidden rounded-md">
                            <img
                                alt="pic"
                                src={W}
                                className="aspect-[3/4] object-cover w-[45px]"
                            />
                        </div>
                        <div className="pt-2 text-muted-foreground ml-5">
                            <h1>News title</h1>
                            <p>News subtitle</p>
                        </div>
                    </figure>
                </div>
                <ScrollBar orientation="vertical" />
                </ScrollArea>
        </Card>
    </>
}